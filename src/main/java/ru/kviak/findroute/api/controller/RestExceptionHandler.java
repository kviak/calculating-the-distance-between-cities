package ru.kviak.findroute.api.controller;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kviak.findroute.exception.CityNotFoundException;
import ru.kviak.findroute.exception.InvalidXmlDataException;
import ru.kviak.findroute.exception.RouteToCityNotFoundException;
import ru.kviak.findroute.exception.error.AppError;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CityNotFoundException.class)
    protected ResponseEntity<?> cityNotFoundException(){
        return ResponseEntity.status(404).body(
                new AppError(404, "City not found! Use '/api/v1/city', for see available city list.")
        );
    }

    @ExceptionHandler(value = RouteToCityNotFoundException.class)
    protected ResponseEntity<?> routeToCityNotFoundException(){
        return ResponseEntity.status(404).body(
                new AppError(404, "Distance for this city not found! Use '/api/v1/city', for see available city list.")
        );
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    protected ResponseEntity<?> dataIntegrityViolationException(){
        return ResponseEntity.status(409).body(
                new AppError(409, "Cities or Distances already exist! Use '/api/v1/city', for see available city list.")
        );
    }

    @ExceptionHandler(value = InvalidXmlDataException.class)
    protected ResponseEntity<?> invalidXmlDataException(){
        return ResponseEntity.status(400).body(
                new AppError(400, "Invalid input file! Use '/api/v1/city', for see available city list.")
        );
    }

    @ExceptionHandler(value = JsonParseException.class)
    protected ResponseEntity<?> jsonParseException(){
        return ResponseEntity.status(400).body(
                new AppError(400, "Invalid data file! Сity or distance has already been detected! " +
                                                 "Use '/api/v1/city', for see available city list.")
        );
    }
}
