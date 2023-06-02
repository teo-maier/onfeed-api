package com.project.onfeedapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerDTO {
    private Long id;

    @Nullable
    private String value;

    @NonNull
    private QuestionDTO question;


    @NonNull
    private EmployeeDTO employee;

    @Nullable
    private List<OptionAnswerDTO> options;
}
