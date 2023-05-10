package com.project.onfeedapi.utils.exception;



public class EmployeeException extends RuntimeException {
    public final ErrorCode errorCode;
    public final String message;

    public EmployeeException(String message, ErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return message;
    }
}
