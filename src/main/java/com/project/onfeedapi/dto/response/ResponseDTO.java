package com.project.onfeedapi.dto.response;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDTO<T> extends AbstractResponseDTO {
    private T body;
    private String message;

    public ResponseDTO(String message) {
        super(message);
    }

    @Builder(builderMethodName = "fullBuilder")
    public ResponseDTO(String message, T body) {
        super(message);
        this.body = body;
    }
}
