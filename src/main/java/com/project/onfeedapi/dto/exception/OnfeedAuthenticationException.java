package com.project.onfeedapi.dto.exception;

import org.springframework.security.core.AuthenticationException;

public class OnfeedAuthenticationException extends AuthenticationException {
    public OnfeedAuthenticationException(String msg) {
        super(msg);
    }
}
