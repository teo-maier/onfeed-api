package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.QuestionDTO;
import com.project.onfeedapi.model.Question;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class QuestionMapper {
    public static QuestionDTO convertToDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .value(question.getValue())
                .answerType(question.getAnswerType())
                .options(question.getOptions().stream().map(OptionMapper::convertToDTO).collect(Collectors.toList()))
                .build();
    }

    public static Question convertToModel(QuestionDTO questionDTO) {
        return Question.builder()
                .id(questionDTO.getId())
                .value(questionDTO.getValue())
                .answerType(questionDTO.getAnswerType())
                .options(new ArrayList<>())
                .build();
    }

    public static Question convertToModelWithOptions(QuestionDTO questionDTO) {
        return Question.builder()
                .id(questionDTO.getId())
                .value(questionDTO.getValue())
                .answerType(questionDTO.getAnswerType())
                .options(questionDTO.getOptions().stream().map(OptionMapper::convertToModel).toList())
                .build();
    }
}
