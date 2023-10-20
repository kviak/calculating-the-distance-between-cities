package ru.kviak.findroute.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kviak.findroute.exception.CityNotFoundException;
import ru.kviak.findroute.exception.RouteToCityNotFoundException;
import ru.kviak.findroute.exception.error.AppError;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CityNotFoundException.class)
    protected ResponseEntity<?> cityNotFoundException(){
        return ResponseEntity.status(404).body(
                new AppError(404, "City not found! Use '/city', for see available city list.")
        );
    }

    @ExceptionHandler(value = RouteToCityNotFoundException.class)
    protected ResponseEntity<?> routeToCityNotFoundException(){
        return ResponseEntity.status(404).body(
                new AppError(404, "Route for this city not found! Use '/city', for see available city list.")
        );
    }
}
