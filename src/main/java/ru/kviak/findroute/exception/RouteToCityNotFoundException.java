package ru.kviak.findroute.exception;

import lombok.experimental.StandardException;

@StandardException
public class RouteToCityNotFoundException extends RuntimeException{
    private String message;
}
