package com.project.onfeedapi.utils.exception;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.dto.exception.ExceptionDTO;
import org.springframework.http.ResponseEntity;

public class CustomExceptionHandler {

    private CustomExceptionHandler() {
        // avoid instantiation
    }

    public static ResponseEntity<ExceptionDTO> handleException(EmployeeException e){
        ExceptionDTO exceptionDTO = ExceptionDTO.fullBuilder()
                .code(e.getErrorCode().getValue())
                .message(e.getErrorMessage())
                .build();
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
