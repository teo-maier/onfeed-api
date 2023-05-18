package com.project.onfeedapi.utils;

public enum SessionType {
    COMPLETED("Completed"),
    IN_PROGRESS("In progress");


    private String value;

    SessionType(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
