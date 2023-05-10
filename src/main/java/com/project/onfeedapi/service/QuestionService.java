package com.project.onfeedapi.service;


import com.project.onfeedapi.dto.QuestionDTO;
import com.project.onfeedapi.mapper.QuestionMapper;
import com.project.onfeedapi.model.Question;
import com.project.onfeedapi.repository.QuestionRepository;
import com.project.onfeedapi.utils.AnswerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final OptionService optionService;

    public List<QuestionDTO> getAllByFormId(Long formId) {
        List<Question> questions = questionRepository.findAllByFormId(formId);
        return questions.stream().map(QuestionMapper::convertToDTO).collect(Collectors.toList());
    }

    public void create(List<QuestionDTO> questionDTOS) {
        // test if options are saved
        List<Question> question = questionDTOS.stream().map((QuestionMapper::convertToModel)).toList();
        List<Question> savedQuestions = questionRepository.saveAll(question);
        saveOptionsIfExists(savedQuestions.stream().map(QuestionMapper::convertToDTO).toList());
    }

    private void saveOptionsIfExists(List<QuestionDTO> questionDTOS) {
        for (QuestionDTO questionDTO : questionDTOS) {
            if (questionDTO.getAnswerType() == AnswerType.SINGLE_SELECT || questionDTO.getAnswerType() == AnswerType.MULTIPLE_SELECT) {
                optionService.create(questionDTO.getOptions());
            }
        }
    }

    public void edit(List<QuestionDTO> questionDTOS, Long formId) {
//        List<QuestionDTO> allQuestions = getAllByFormId(formId);
//        for (QuestionDTO questionDTO : questionDTOS) {
//            allQuestions.stream().filter(question -> Objects.equals(question.getId(), questionDTO.getId())).map(questionDTO1 -> {
//                questionDTO1.setValue(questionDTO.getValue());
//                questionDTO1.setAnswerType(questionDTO.getAnswerType());
//                questionDTO1
//            })
//        }
//        // test if options are saved
//        saveOptionsIfExists(questionDTOS);
//        List<Question> question = questionDTOS.stream().map((QuestionMapper::convertToModel)).toList();
//        questionRepository.saveAll(question);
    }
}
