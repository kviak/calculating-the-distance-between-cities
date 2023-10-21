package ru.kviak.findroute.service;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.kviak.findroute.model.CalculationType;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StringToEnumConverterTest {

    private StringToEnumConverter converter;

    @BeforeEach
    public void setup() {
        converter = new StringToEnumConverter();
    }

    @Test
    public void convert_shouldReturnADD() {
        // Arrange
        String source = "Crowflight";
        CalculationType expected = CalculationType.CROWFLIGHT;

        // Act
        CalculationType result = converter.convert(source);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void convert_shouldReturnSUBTRACT() {
        // Arrange
        String source = "Distance Matrix";
        CalculationType expected = CalculationType.DISTANCE_MATRIX;

        // Act
        CalculationType result = converter.convert(source);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void convert_shouldReturnMULTIPLY() {
        // Arrange
        String source = "All";
        CalculationType expected = CalculationType.ALL;

        // Act
        CalculationType result = converter.convert(source);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void convert_shouldReturnMODULO() {
        // Arrange
        String source = "WRAP";
        CalculationType expected = null;

        // Act
        CalculationType result = converter.convert(source);

        // Assert
        assertEquals(expected, result);
    }
}
