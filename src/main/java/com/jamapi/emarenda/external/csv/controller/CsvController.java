package com.jamapi.emarenda.external.csv.controller;

import com.jamapi.emarenda.domain.response_message.ResponseMessage;
import com.jamapi.emarenda.external.csv.service.CsvReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    private final CsvReaderService csvReaderService;

    public CsvController(CsvReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadCsv(@RequestParam("file") MultipartFile file) {
        var message = csvReaderService.readUsersFromCsv(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpStatus.CREATED,message));
    }
}

