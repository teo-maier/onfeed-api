package com.project.onfeedapi.service;

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

@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionRepository optionRepository;


    public List<Option> create(List<OptionDTO> optionDTOList) {
        List<Option> options = optionDTOList.stream().map((OptionMapper::convertToModel)).toList();
        return optionRepository.saveAll(options);
    }

}
