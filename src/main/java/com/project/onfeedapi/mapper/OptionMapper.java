package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.OptionAnswerDTO;
import com.project.onfeedapi.dto.OptionDTO;
import com.project.onfeedapi.model.Option;
import com.project.onfeedapi.model.OptionAnswer;

public class OptionMapper {
    public static OptionDTO convertToDTO(Option option) {
        return OptionDTO.builder()
                .id(option.getId())
                .value(option.getValue())
                .build();
    }

    public static Option convertToModel(OptionDTO optionDTO) {
        return Option.builder()
                .id(optionDTO.getId())
                .value(optionDTO.getValue())
                .build();
    }

    public static OptionAnswer convertToOptionAnswerModel(OptionAnswerDTO optionDTO) {
        return OptionAnswer.builder()
                .id(optionDTO.getId())
                .value(optionDTO.getValue())
                .build();
    }

    public static OptionAnswerDTO convertToOptionAnswerDTO(OptionAnswer option) {
        return OptionAnswerDTO.builder()
                .id(option.getId())
                .value(option.getValue())
                .build();
    }
}
