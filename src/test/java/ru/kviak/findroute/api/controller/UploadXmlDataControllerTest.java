package ru.kviak.findroute.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.kviak.findroute.exception.InvalidXmlDataException;
import ru.kviak.findroute.service.XmlParserService;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UploadXmlDataControllerTest {

    @Mock
    private XmlParserService xmlParserService;

    @InjectMocks
    private UploadXmlDataController xmlController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadXMLData() throws Exception {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>...</root>";
        byte[] xmlBytes = xmlData.getBytes();
        MultipartFile multipartFile = new MockMultipartFile("file", xmlBytes);

        doNothing().when(xmlParserService).parseCitiesAndDistances((InputStream) any());

        ResponseEntity<String> response = xmlController.uploadXMLData(multipartFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cities and distances added successfully", response.getBody());

        verify(xmlParserService, times(1)).parseCitiesAndDistances((InputStream) any());
    }

    @Test
    public void testUploadXMLData_InvalidXmlDataException() throws Exception {
        String errorMsg = "Invalid input data";
        doThrow(new InvalidXmlDataException(errorMsg)).when(xmlParserService).parseCitiesAndDistances(any());

        MultipartFile multipartFile = new MockMultipartFile("file", new byte[0]);

        InvalidXmlDataException exception = assertThrows(InvalidXmlDataException.class, () -> {
            xmlController.uploadXMLData(multipartFile);
        });

        assertEquals(errorMsg, exception.getMessage());
    }
}
