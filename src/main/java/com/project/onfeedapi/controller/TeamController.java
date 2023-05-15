package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.service.TeamService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("team")
public class TeamController {
    private final TeamService teamService;


    @GetMapping("/all/{managerId}")
    public ResponseEntity<AbstractResponseDTO> getTeamsByManager(@PathVariable("managerId") Long managerId) {
        return ResponseHandler.handleResponse("Get all teams by manager", teamService.getTeamsByManager(managerId));
    }

    @GetMapping("/all")
    public ResponseEntity<AbstractResponseDTO> getAllTeams() {
        return ResponseHandler.handleResponse("Get all teams", teamService.getAllTeams());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<AbstractResponseDTO> getTeamById(@PathVariable("teamId") long id) {
        return ResponseHandler.handleResponse("Get team by id", teamService.getTeamById(id));
    }
}
