package com.project.onfeedapi.security.utils;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs1927";
    public static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String ACCESS_HEADER_STRING = "Access-Control-Expose-Headers";
    public static final String ROLE = "Role";
    public static final String AUTHORITIES_KEY = "RoleKy";
    public static final String LOGGED_IN_USER_ID_HEADER = "id";
    public static final String LOGGED_IN_USER_AFTER_LOGIN_ROUTE_HEADER = "user_route";
    public static final String FIRST_LOGIN = "firstLogin";
}
