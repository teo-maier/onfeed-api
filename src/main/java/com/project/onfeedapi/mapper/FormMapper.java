package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.FormDTO;
import com.project.onfeedapi.model.Form;

import java.util.stream.Collectors;

public class FormMapper {

    public static FormDTO convertToDTO(Form form) {
        return FormDTO.builder()
                .id(form.getId())
                .title(form.getTitle())
                .description(form.getDescription())
//                .questions(form.getQuestions().stream().map(QuestionMapper::convertToDTO).collect(Collectors.toList()))
                .build();
    }

    public static Form convertToModel(FormDTO formDTO) {
        return Form.builder()
                .id(formDTO.getId())
                .title(formDTO.getTitle())
                .description(formDTO.getDescription())
//                .questions(formDTO.getQuestions().stream().map(QuestionMapper::convertToModel).collect(Collectors.toList()))
                .build();
    }
}
