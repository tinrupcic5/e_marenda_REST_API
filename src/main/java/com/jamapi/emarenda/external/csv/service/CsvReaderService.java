package com.jamapi.emarenda.external.csv.service;

import com.jamapi.emarenda.exception.CsvProcessingException;
import com.jamapi.emarenda.external.csv.UserCsvModel;
import com.jamapi.emarenda.rbac.model.UserCsvDto;
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

    public CsvReaderService(UserService userService) {
        this.userService = userService;
    }

    public String readUsersFromCsv(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<UserCsvModel> csvToBean = new CsvToBeanBuilder<UserCsvModel>(reader)
                    .withType(UserCsvModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<UserCsvDto> userDtos = csvToBean.parse().stream()
                    .map(UserCsvModel::toUserCsvDto)
                    .toList();
            // TODO get child oibs from parrent
            //  get child where oib is
            //  save parents(users)
            //  save parent child
//            userDtos.forEach(userService::saveUser);

            return "CSV values are saved.";
        } catch (Exception e) {
            throw new CsvProcessingException("Error reading CSV file: " + e.getMessage(), e);
        }
    }
}
