package com.project.onfeedapi.dto.exception;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO extends RuntimeException {
    private int code;
    private String message;
    private HttpStatus httpStatus;

    public ExceptionDTO(String message) {
        super(message);
    }

    @Builder(builderMethodName = "fullBuilder")
    public ExceptionDTO(String message,HttpStatus httpStatus, int code) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
