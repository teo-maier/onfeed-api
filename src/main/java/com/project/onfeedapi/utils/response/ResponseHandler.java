package com.project.onfeedapi.utils.response;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.dto.exception.ResponseDTO;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    private ResponseHandler() {
        // avoid instantiation
    }

    public static <T> ResponseEntity<AbstractResponseDTO> handleResponse(String message, T body) {
        ResponseDTO<Object> responseDTO = ResponseDTO.fullBuilder()
                .message(message)
                .body(body)
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}
