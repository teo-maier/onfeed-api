package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.TeamDTO;
import com.project.onfeedapi.dto.TeamMemberDTO;
import com.project.onfeedapi.mapper.TeamMapper;
import com.project.onfeedapi.repository.TeamRepository;
import com.project.onfeedapi.utils.exception.EmployeeException;
import com.project.onfeedapi.utils.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll(Sort.by("name").ascending())
                .stream().map(TeamMapper::convertToDTO).collect(Collectors.toList());
    }

    public List<TeamDTO> getTeamsByManager(Long managerId) {
        if (managerId == null) {
            throw new EmployeeException("Manager does not exist!", ErrorCode.NOT_FOUND);
        }
        PageRequest sortTeamsByName = PageRequest.of(0, 1000, Sort.by("name").ascending());
        return teamRepository.findTeamsByManager(managerId, sortTeamsByName)
                .stream().map(TeamMapper::convertToDTO).collect(Collectors.toList());
    }

    public TeamDTO getTeamById(long id) throws EmployeeException {
        return TeamMapper.convertToDTO(teamRepository.findById(id).orElseThrow(() -> new EmployeeException(
                String.format("No Team with id %d", id),
                ErrorCode.NOT_FOUND
        )));
    }
}
