package com.project.onfeedapi.utils;

public class StringUtils {
    public static String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static boolean isMatch(String s1, String s2) {
        return s1.matches(s2);
    }
}
