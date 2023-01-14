package com.fikrat.hrms.exception;

public class ManagerAlreadyExistsException extends RuntimeException {
    public ManagerAlreadyExistsException(String message) {
        super(message);
    }
}
