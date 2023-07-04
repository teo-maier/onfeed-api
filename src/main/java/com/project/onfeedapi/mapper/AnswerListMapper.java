package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.AnswerDTO;
import com.project.onfeedapi.dto.AnswerListDTO;
import com.project.onfeedapi.model.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerListMapper {
    public static AnswerListDTO convertListToDTO(List<Answer> answerList) {
        AnswerListDTO answerListDTO = new AnswerListDTO();
        for (Answer answer : answerList) {
            AnswerDTO answerDTO = AnswerDTO.builder()
                    .id(answer.getId())
                    .value(answer.getValue())
                    .employee(EmployeeMapper.convertToDTO(answer.getEmployee()))
                    .question(QuestionMapper.convertToDTO(answer.getQuestion()))
                    .session(SessionMapper.convertToDTO(answer.getSession()))
                    .build();
            answerListDTO.getAnswers().add(answerDTO);
        }
        return answerListDTO;
    }

    public static AnswerDTO convertToDTO(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .value(answer.getValue())
                .employee(EmployeeMapper.convertToDTO(answer.getEmployee()))
                .question(QuestionMapper.convertToDTO(answer.getQuestion()))
                .session(SessionMapper.convertToDTO(answer.getSession()))
                .build();
    }

    public static List<Answer> convertListToModel(AnswerListDTO answerListDTO) {
        List<Answer> answerList = new ArrayList<>();
        for (AnswerDTO answerDTO : answerListDTO.getAnswers()) {
            answerList.add(Answer.builder()
                    .id(answerDTO.getId())
                    .value(answerDTO.getValue())
                    .employee(EmployeeMapper.convertToModel(answerDTO.getEmployee()))
                    .question(QuestionMapper.convertToModel(answerDTO.getQuestion()))
                    .session(SessionMapper.convertToModel(answerListDTO.getSession()))
                    .options(new ArrayList<>())
                    .build());
        }
        return answerList;
    }
}
