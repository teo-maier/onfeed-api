package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.SessionDTO;
import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.service.SessionService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("session")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping("/all")
    public ResponseEntity<AbstractResponseDTO> getAll() {
        return ResponseHandler.handleResponse("All sessions", sessionService.getAll());
    }

    @GetMapping("/all/not-completed")
    public ResponseEntity<AbstractResponseDTO> getAllNotCompletedSessionsByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        return ResponseHandler.handleResponse("All recipients by employee id", sessionService.getAllNotCompletedByEmployeeId(employeeId));
    }

    @GetMapping("/all/completed")
    public ResponseEntity<AbstractResponseDTO> getAllCompletedSessionsByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        return ResponseHandler.handleResponse("All recipients by employee id", sessionService.getAllCompletedByEmployeeId(employeeId));
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<AbstractResponseDTO> getById(@PathVariable Long sessionId) {
        return ResponseHandler.handleResponse("Session by id", sessionService.getById(sessionId));
    }

    @PostMapping
    public ResponseEntity<AbstractResponseDTO> create(@RequestBody SessionDTO sessionDTO) {
        return ResponseHandler.handleResponse("Session created successfully", sessionService.create(sessionDTO));
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<AbstractResponseDTO> edit(@PathVariable Long sessionId, @RequestBody SessionDTO editedSession) {
        return ResponseHandler.handleResponse("Session updated successfully", sessionService.edit(sessionId, editedSession));
    }
}
