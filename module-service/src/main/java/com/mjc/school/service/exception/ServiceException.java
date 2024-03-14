package com.mjc.school.service.exception;

import java.io.Serial;

public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorCode;

    public ServiceException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return "ERROR CODE:" + this.getErrorCode() + " : " + super.getMessage();
    }
}
