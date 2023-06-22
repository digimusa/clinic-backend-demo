package com.cms.clinic.exception;

public class UserDoesNotExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserDoesNotExistException() {
        super("The email does not exists");
    }
}
