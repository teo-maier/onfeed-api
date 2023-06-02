package com.project.onfeedapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessionResultsDTO {
    int answered;

    int notAnswered;

    int total;

}
