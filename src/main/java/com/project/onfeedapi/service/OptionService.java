package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.FormDTO;
import com.project.onfeedapi.dto.OptionDTO;
import com.project.onfeedapi.dto.QuestionDTO;
import com.project.onfeedapi.mapper.OptionMapper;
import com.project.onfeedapi.mapper.QuestionMapper;
import com.project.onfeedapi.model.Option;
import com.project.onfeedapi.model.Question;
import com.project.onfeedapi.repository.OptionRepository;
import com.project.onfeedapi.utils.AnswerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionRepository optionRepository;


    public void setOptionsToQuestion(FormDTO formDTO, Question question) {
        for (QuestionDTO q : formDTO.getQuestions()) {
            if (Objects.equals(q.getValue(), question.getValue())) {
                if (q.getAnswerType() == AnswerType.SINGLE_SELECT || q.getAnswerType() == AnswerType.MULTIPLE_SELECT) {
                    List<Option> optionList = q.getOptions().stream().map(OptionMapper::convertToModel).toList();
                    for (Option o : optionList) {
                        question.addOption(o);
                    }
                }
            }
        }
    }

    public List<OptionDTO> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findByQuestionId(questionId).stream().map(OptionMapper::convertToDTO).toList();
    }

}
