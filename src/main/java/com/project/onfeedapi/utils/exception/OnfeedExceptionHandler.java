package com.project.onfeedapi.utils.exception;

import com.project.onfeedapi.dto.exception.ExceptionDTO;
import com.project.onfeedapi.dto.exception.OnfeedAuthenticationException;
import com.project.onfeedapi.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
public class OnfeedExceptionHandler {

    @ExceptionHandler(ExceptionDTO.class)
    public ResponseEntity<ExceptionDTO> handleHaSException(ExceptionDTO ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.fullBuilder()
                .code(ex.getHttpStatus().value())
                .message(ex.getMessage())
                .build();
//        return ResponseEntity.status(exceptionDTO.getCode()).body(exceptionDTO);
        return new ResponseEntity<>(new ExceptionDTO(exceptionDTO.getMessage()), exceptionDTO.getHttpStatus());

    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ExceptionDTO> handleEmployeeException(ExceptionDTO ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.fullBuilder()
                .code(ex.getHttpStatus().value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(exceptionDTO.getCode()).body(exceptionDTO);
    }

    @ExceptionHandler(OnfeedAuthenticationException.class)
    public ResponseEntity<ResponseDTO> handleHaSAuthenticationException(
            OnfeedAuthenticationException ex
    ) {
        return new ResponseEntity<>(new ResponseDTO(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> handleIllegalArgumentException(
            IllegalArgumentException ex
    ) {
        return new ResponseEntity<>(new ResponseDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleAccessExceptions(AccessDeniedException ex) {
        return new ResponseEntity<>(new ResponseDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleExceptions(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ResponseDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            Locale locale
    ) {
        ExceptionDTO exceptionDTO = ExceptionDTO.fullBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(exceptionDTO.getCode()).body(exceptionDTO);
    }
}