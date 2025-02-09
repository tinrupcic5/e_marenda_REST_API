package com.jamapi.emarenda.external.csv.service;

import com.jamapi.emarenda.domain.parent_child.model.ParentChildModel;
import com.jamapi.emarenda.domain.parent_child.service.ParentChildService;
import com.jamapi.emarenda.exception.CsvProcessingException;
import com.jamapi.emarenda.exception.UserNotFoundException;
import com.jamapi.emarenda.external.csv.UserCsvModel;
import com.jamapi.emarenda.rbac.model.UserCsvDto;
import com.jamapi.emarenda.rbac.model.UserDto;
import com.jamapi.emarenda.rbac.model.UserModel;
import com.jamapi.emarenda.rbac.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CsvReaderService {
    private final UserService userService;
    private final ParentChildService parentChildService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvReaderService.class);

    public CsvReaderService(UserService userService, ParentChildService parentChildService) {
        this.userService = userService;
        this.parentChildService = parentChildService;
    }

    @Transactional
    public String readUsersFromCsv(MultipartFile file) {
        LOGGER.info("Starting to process CSV file: {}", file.getOriginalFilename());
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<UserCsvModel> csvToBean = new CsvToBeanBuilder<UserCsvModel>(reader)
                    .withType(UserCsvModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // Get all users
            List<UserCsvDto> userCsvDto = csvToBean.parse().stream()
                    .map(UserCsvModel::toUserCsvDto)
                    .toList();
            LOGGER.info("Parsed {} user records from the CSV.", userCsvDto.size());

            // Map them into users DTOs to save them as entities
            List<UserDto> userDtos = userCsvDto.stream()
                    .map(UserCsvDto::toUserDto)
                    .toList();

            // Save entities
            userDtos.forEach(userService::saveUser);
            LOGGER.info("Successfully saved {} user records.", userDtos.size());

            saveParentChildRelationship(userCsvDto);

            LOGGER.info("CSV processing completed successfully.");
            return "CSV values are saved.";
        } catch (Exception e) {
            LOGGER.error("Error processing CSV file: {}", e.getMessage(), e);
            throw new CsvProcessingException("Error reading CSV file: " + e.getMessage(), e);
        }
    }

    private void saveParentChildRelationship(List<UserCsvDto> userCsvDtoList) {
        LOGGER.info("Starting to process parent-child relationships.");

        // Filter out users who have children
        List<UserCsvDto> parentsWithChildren = userCsvDtoList
                .stream()
                .filter(user -> user.getChildOib() != null && !user.getChildOib().isEmpty())
                .toList();

        LOGGER.info("Found {} parents with children to process.", parentsWithChildren.size());

        for (UserCsvDto parentDto : parentsWithChildren) {
            // Find the parent by OIB
            UserModel parent = userService.findByOib(parentDto.getOib());
            if (parent == null) {
                throw new UserNotFoundException("Parent with OIB " + parentDto.getOib() + " not found.");
            }
            LOGGER.info("Parent with OIB {} found.", parentDto.getOib());

            for (String childOib : parentDto.getChildOib()) {
                // Find each child by OIB
                UserModel child = userService.findByOib(childOib);
                if (child == null) {
                    throw new UserNotFoundException("Child with OIB " + childOib + " not found.");
                }

                // Create and save parent-child relationship
                ParentChildModel relationship = new ParentChildModel(parent, child);
                parentChildService.saveRelationship(relationship);
                LOGGER.info("Created parent-child relationship: Parent OIB = {}, Child OIB = {}.",
                        parentDto.getOib(), childOib);
            }
        }

        LOGGER.info("Parent-child relationship processing completed.");
    }
}
