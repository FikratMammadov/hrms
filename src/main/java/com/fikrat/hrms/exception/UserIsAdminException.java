package com.fikrat.hrms.exception;

public class UserIsAdminException extends RuntimeException {
    public UserIsAdminException(String message) {
        super(message);
    }
}
