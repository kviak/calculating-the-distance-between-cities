package ru.kviak.findroute.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.kviak.findroute.exception.error.AppError;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestExceptionHandlerTest {

    private final RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

    @Test
    public void testCityNotFoundException() {
        ResponseEntity<?> response = restExceptionHandler.cityNotFoundException();
        AppError expectedError = new AppError(404, "City not found! Use '/api/v1/city', for see available city list.");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedError.toString(), response.getBody().toString());
    }

    @Test
    public void testRouteToCityNotFoundException() {
        ResponseEntity<?> response = restExceptionHandler.routeToCityNotFoundException();
        AppError expectedError = new AppError(404, "Distance for this city not found! Use '/api/v1/city', for see available city list.");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedError.toString(), response.getBody().toString());
    }

    @Test
    public void testDataIntegrityViolationException() {
        ResponseEntity<?> response = restExceptionHandler.dataIntegrityViolationException();
        AppError expectedError = new AppError(409, "Cities or Distances already exist! Use '/api/v1/city', for see available city list.");

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(expectedError.toString(), response.getBody().toString());
    }

    @Test
    public void testInvalidXmlDataException() {
        ResponseEntity<?> response = restExceptionHandler.invalidXmlDataException();
        AppError expectedError = new AppError(400, "Invalid input file! Use '/api/v1/city', for see available city list.");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedError.toString(), response.getBody().toString());
    }

    @Test
    public void testJsonParseException() {
        ResponseEntity<?> response = restExceptionHandler.jsonParseException();
        AppError expectedError = new AppError(400, "Invalid data file! Сity or distance has already been detected! Use '/api/v1/city', for see available city list.");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedError.toString(), response.getBody().toString());
    }
}

