package ru.kviak.findroute.exception;

import lombok.experimental.StandardException;

@StandardException
public class CityNotFoundException extends RuntimeException {
    private String message;
}
