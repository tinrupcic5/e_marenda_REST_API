package com.jamapi.emarenda.external.csv.service;

import com.jamapi.emarenda.domain.parent_child.model.ParentChildModel;
import com.jamapi.emarenda.domain.parent_child.service.ParentChildService;
import com.jamapi.emarenda.exception.CsvProcessingException;
import com.jamapi.emarenda.external.csv.UserCsvModel;
import com.jamapi.emarenda.rbac.model.UserCsvDto;
import com.jamapi.emarenda.rbac.model.UserDto;
import com.jamapi.emarenda.rbac.model.UserModel;
import com.jamapi.emarenda.rbac.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvReaderService {
    private final UserService userService;
    private final ParentChildService parentChildService;

    public CsvReaderService(UserService userService, ParentChildService parentChildService) {
        this.userService = userService;
        this.parentChildService = parentChildService;
    }

    public String readUsersFromCsv(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<UserCsvModel> csvToBean = new CsvToBeanBuilder<UserCsvModel>(reader)
                    .withType(UserCsvModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // get all users
            List<UserCsvDto> userCsvDto = csvToBean.parse().stream()
                    .map(UserCsvModel::toUserCsvDto)
                    .toList();
            // map them into users dto to save them as entities
            List<UserDto> userDtos = userCsvDto.stream()
                    .map(UserCsvDto::toUserDto)
                    .toList();
            // save entities
            userDtos.forEach(userService::saveUser);

            saveParentChildRelationship(userCsvDto);

            return "CSV values are saved.";
        } catch (Exception e) {
            throw new CsvProcessingException("Error reading CSV file: " + e.getMessage(), e);
        }
    }

    private void saveParentChildRelationship(List<UserCsvDto> userCsvDto) {
        // filter out that ones that has set of child oibs
        List<UserCsvDto> u = userCsvDto
                .stream()
                .filter(userCsvDto1 -> !userCsvDto1.getChildOib().isEmpty())
                .toList();
        // find those with oibs
        UserModel userModel = userService.findByOib("");

        // TODO save parents and child relationship
        parentChildService.saveRelationship( new ParentChildModel());
    }
}
