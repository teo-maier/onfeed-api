package com.project.onfeedapi.utils;


public enum AnswerType {
    SINGLE_SELECT("SINGLE_SELECT"),
    MULTIPLE_SELECT("MULTIPLE_SELECT"),
    TEXTAREA("TEXTAREA"),
    EMOJI("EMOJI"),
    STAR("STAR");

    private String value;

    AnswerType(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
