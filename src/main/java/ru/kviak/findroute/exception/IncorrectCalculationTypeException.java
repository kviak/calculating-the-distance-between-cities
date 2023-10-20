package ru.kviak.findroute.exception;

import lombok.experimental.StandardException;

@StandardException
public class IncorrectCalculationTypeException extends RuntimeException {
    private String message;
}