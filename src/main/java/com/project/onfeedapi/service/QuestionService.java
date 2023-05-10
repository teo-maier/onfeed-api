package com.project.onfeedapi.service;


import com.project.onfeedapi.dto.FormDTO;
import com.project.onfeedapi.dto.OptionDTO;
import com.project.onfeedapi.dto.QuestionDTO;
import com.project.onfeedapi.mapper.OptionMapper;
import com.project.onfeedapi.mapper.QuestionMapper;
import com.project.onfeedapi.model.Form;
import com.project.onfeedapi.model.Option;
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

    public List<QuestionDTO> getQuestions(Long formId) {
        List<Question> questions = questionRepository.findAllByFormId(formId);
        return questions.stream().map(QuestionMapper::convertToDTO).collect(Collectors.toList());
    }

    public void setFormToQuestions(FormDTO formDTO, Form form) {
        List<Question> questionList = formDTO.getQuestions().stream().map(QuestionMapper::convertToModel).toList();
        for (Question question : questionList) {
            form.addQuestion(question);
            optionService.setOptionsToQuestion(formDTO, question);
        }
    }

}
