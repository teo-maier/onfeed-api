package com.project.onfeedapi.utils;


public enum AnswerType {
    SINGLE_SELECT("Single select"),
    MULTIPLE_SELECT("Multiple select"),
    TEXTAREA("Textarea"),
    EMOJI("Emoji"),
    STAR("Star");

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
