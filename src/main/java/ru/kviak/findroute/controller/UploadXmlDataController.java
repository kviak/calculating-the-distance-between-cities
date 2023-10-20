package ru.kviak.findroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kviak.findroute.service.XmlParserService;

@RestController
@RequiredArgsConstructor
public class UploadXmlDataController {
    private final XmlParserService xmlParserService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadXMLData(@RequestParam("file") MultipartFile file) {
        try {
            xmlParserService.parseCitiesAndDistances(file.getInputStream());
            return ResponseEntity.ok("Cities and distances added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
