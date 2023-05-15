package com.project.onfeedapi.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamDTO {
    private Long id;

    private String teamName;

    private List<TeamMemberDTO> members;
}