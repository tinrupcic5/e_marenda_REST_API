package com.teapot.emarenda.external.csv.controller;

import com.teapot.emarenda.domain.response_message.ResponseMessage;
import com.teapot.emarenda.external.csv.service.CsvReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvController.class);


    private final CsvReaderService csvReaderService;

    public CsvController(CsvReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadCsv(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Starting to upload csv file: {}", file.getOriginalFilename());
        var message = csvReaderService.readUsersFromCsv(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpStatus.CREATED,message));
    }
}

