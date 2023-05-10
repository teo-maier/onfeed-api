package com.project.onfeedapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.onfeedapi.utils.AnswerType;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDTO {
    private Long id;

    private String value;

    private AnswerType answerType;

    private FormDTO form;

    @Nullable
    private List<OptionDTO> options;
}
