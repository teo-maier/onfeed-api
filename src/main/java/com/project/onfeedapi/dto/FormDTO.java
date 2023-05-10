package com.project.onfeedapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormDTO {
    private Long id;

    private String title;

    private String description;

    private List<QuestionDTO> questions;
}
