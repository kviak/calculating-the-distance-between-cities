package ru.kviak.findroute.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.kviak.findroute.exception.InvalidXmlDataException;
import ru.kviak.findroute.service.XmlParserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/upload")
public class UploadXmlDataController {
    private final XmlParserService xmlParserService;

    @PostMapping
    public ResponseEntity<String> uploadXMLData(@RequestParam("file") MultipartFile file) {
        try {
            xmlParserService.parseCitiesAndDistances(file.getInputStream());
            return ResponseEntity.ok("Cities and distances added successfully");
        } catch (Exception e) {
            throw new InvalidXmlDataException("Invalid input data", e);
        }
    }
}
