package com.project.onfeedapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionDTO {
    private Long id;

    private String value;

    private QuestionDTO question;
}
