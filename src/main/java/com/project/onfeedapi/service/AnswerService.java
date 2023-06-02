package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.AnswerDTO;
import com.project.onfeedapi.dto.AnswerListDTO;
import com.project.onfeedapi.mapper.AnswerListMapper;
import com.project.onfeedapi.mapper.OptionMapper;
import com.project.onfeedapi.model.Answer;
import com.project.onfeedapi.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final OptionAnswerService optionAnswerService;

    private final QuestionService questionService;

    private final EmployeeService employeeService;

    private final SessionRecipientService sessionRecipientService;

    public AnswerDTO getByQuestionId(Long questionId, Long employeeId) {
        Answer answer = answerRepository.getByQuestionIdAndEmployeeId(questionId, employeeId);
        AnswerDTO answerDTO = AnswerListMapper.convertToDTO(answer);
        answerDTO.setOptions((answer.getOptions().stream().map(OptionMapper::convertToOptionAnswerDTO).toList()));
        return answerDTO;
    }

    public AnswerListDTO create(AnswerListDTO answerListDTO) {
        for (AnswerDTO answerDTO : answerListDTO.getAnswers()) {
            answerDTO.setQuestion(questionService.getById(answerDTO.getQuestion().getId()));
            answerDTO.setEmployee(employeeService.getEmployee(answerDTO.getEmployee().getId()));
        }
        List<Answer> answerList = AnswerListMapper.convertListToModel(answerListDTO);
        optionAnswerService.setOptionsToAnswer(answerListDTO, answerList);
        answerRepository.saveAll(answerList);
        sessionRecipientService.setCompleted(answerList.get(0).getQuestion().getId(), answerList.get(0).getEmployee().getId());
        return answerListDTO;
    }
}
