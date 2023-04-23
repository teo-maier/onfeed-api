package com.project.onfeedapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO extends AbstractResponseDTO {
    private int code;
    private String message;

    public ExceptionDTO(String message) {
        super(message);
    }

    @Builder(builderMethodName = "fullBuilder")
    public ExceptionDTO(String message, int code) {
        super(message);
        this.code = code;
    }
}
