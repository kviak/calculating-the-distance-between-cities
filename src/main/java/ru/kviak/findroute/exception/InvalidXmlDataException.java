package ru.kviak.findroute.exception;

import lombok.experimental.StandardException;

@StandardException
public class InvalidXmlDataException extends RuntimeException {
    private String message;
}