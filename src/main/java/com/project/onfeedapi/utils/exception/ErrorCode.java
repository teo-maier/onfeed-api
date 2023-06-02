package com.project.onfeedapi.utils.exception;

public enum ErrorCode {
    BAD_REQUEST(400),
    NOT_FOUND(404),
    ALREADY_EXISTS(410),
    GENERAL_ERROR(400);


    private int value;

    ErrorCode(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
