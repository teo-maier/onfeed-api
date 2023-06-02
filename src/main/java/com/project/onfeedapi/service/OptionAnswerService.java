package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.AnswerDTO;
import com.project.onfeedapi.dto.AnswerListDTO;
import com.project.onfeedapi.mapper.OptionMapper;
import com.project.onfeedapi.model.Answer;
import com.project.onfeedapi.model.OptionAnswer;
import com.project.onfeedapi.repository.OptionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OptionAnswerService {

    private final OptionAnswerRepository optionAnswerRepository;

    public void setOptionsToAnswer(AnswerListDTO answerListDTO, List<Answer> answerList) {
        for (Answer answer : answerList) {
            AnswerDTO foundRightAnswer = answerListDTO.getAnswers().stream().filter(e ->
                    Objects.equals(e.getQuestion().getId(), answer.getQuestion().getId())).findAny().orElse(null);
            if (Objects.nonNull(foundRightAnswer)) {
                List<OptionAnswer> optionList = foundRightAnswer.getOptions().stream().map(OptionMapper::convertToOptionAnswerModel).toList();
                for (OptionAnswer o : optionList) {
                    answer.addOption(o);
                    answer.setValue("OPTIONS");
                }
            }
        }
    }
}
