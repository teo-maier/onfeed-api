package com.project.onfeedapi.mapper;

import com.project.onfeedapi.dto.TeamDTO;
import com.project.onfeedapi.dto.TeamMemberDTO;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.model.Team;

import java.util.Objects;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = TeamDTO.builder()
                .id(team.getId())
                .teamName(team.getName())
                .build();

        if (Objects.nonNull(team.getMembers())) {
            teamDTO.setMembers(team.getMembers().stream()
                    .map(teamMember -> {
                        Employee employee = teamMember.getEmployee();
                        return TeamMemberDTO.builder()
                                .id(employee.getId())
                                .firstName(employee.getFirstName())
                                .lastName(employee.getLastName())
                                .active(employee.isActive())
                                .manager(teamMember.isManager())
                                .build();
                    })
                    .collect(Collectors.toList()));
        }

        return teamDTO;
    }

    public static Team convertToModel(TeamDTO teamDTO) {
        return Team.builder()
                .id(teamDTO.getId())
                .name(teamDTO.getTeamName())
                .build();
    }
}
