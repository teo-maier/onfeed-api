package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.FormDTO;
import com.project.onfeedapi.dto.QuestionDTO;
import com.project.onfeedapi.model.Form;
import com.project.onfeedapi.model.Question;

import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionMapper {
    public static QuestionDTO convertToDTO(Question question) {
        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(question.getId())
                .value(question.getValue())
                .answerType(question.getAnswerType())
//                .form(FormMapper.convertToDTO(question.getForm()))
//                .options(question.getOptions().stream().map(OptionMapper::convertToDTO).collect(Collectors.toList()))
                .build();
        if (Objects.nonNull(question.getForm())) {
            FormDTO formDTO = FormMapper.convertToDTO(question.getForm());
            questionDTO.setForm(formDTO);
        }
        return questionDTO;
    }

    public static Question convertToModel(QuestionDTO questionDTO) {
        Question question = Question.builder()
                .id(questionDTO.getId())
                .value(questionDTO.getValue())
                .answerType(questionDTO.getAnswerType())
//                .form(FormMapper.convertToModel(questionDTO.getForm()))
//                .options(questionDTO.getOptions().stream().map(OptionMapper::convertToModel).collect(Collectors.toList()))
                .build();
        if (Objects.nonNull(questionDTO.getForm())) {
            Form form = FormMapper.convertToModel(questionDTO.getForm());
            question.setForm(form);
        }
        return question;
    }
}
