package ru.kviak.findroute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kviak.findroute.exception.InvalidXmlDataException;
import ru.kviak.findroute.persistence.repository.CityRepository;
import ru.kviak.findroute.persistence.repository.DistanceRepository;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class XmlParserServiceTest {
    @Mock
    private CityRepository cityRepository;
    @Mock
    private DistanceRepository distanceRepository;

    private XmlParserService xmlParserService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        xmlParserService = new XmlParserService(cityRepository, distanceRepository);
    }

    @Test
    public void parseCitiesAndDistances_InvalidInputData_ThrowsInvalidXmlDataException() {
        InputStream xmlInput = InputStream.nullInputStream();

        try {
            xmlParserService.parseCitiesAndDistances(xmlInput);

            fail("Expected InvalidXmlDataException to be thrown");
        } catch (InvalidXmlDataException e) {
            assertEquals("Invalid input data", e.getMessage());
        }
    }
}
