package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.OptionDTO;
import com.project.onfeedapi.model.Option;

public class OptionMapper {
    public static OptionDTO convertToDTO(Option option) {
        return OptionDTO.builder()
                .id(option.getId())
                .value(option.getValue())
                .question(QuestionMapper.convertToDTO(option.getQuestion()))
                .build();
    }

    public static Option convertToModel(OptionDTO optionDTO) {
        return Option.builder()
                .id(optionDTO.getId())
                .value(optionDTO.getValue())
                .question(QuestionMapper.convertToModel(optionDTO.getQuestion()))
                .build();
    }
}
