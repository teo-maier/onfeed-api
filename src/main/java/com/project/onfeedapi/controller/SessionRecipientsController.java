package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.service.SessionRecipientService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("session-recipients")
public class SessionRecipientsController {

    private final SessionRecipientService sessionRecipientService;

    @GetMapping
    public ResponseEntity<AbstractResponseDTO> getAllBySessionId(@RequestParam("sessionId") Long sessionId) {
        return ResponseHandler.handleResponse("All recipients by session id", sessionRecipientService.getAllBySessionId(sessionId));
    }

    @GetMapping("/all-by-employee")
    public ResponseEntity<AbstractResponseDTO> getAllByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        return ResponseHandler.handleResponse("All recipients by session id", sessionRecipientService.getAllByEmployeeId(employeeId));
    }

    @GetMapping("/results")
    public ResponseEntity<AbstractResponseDTO> getSessionResultBySessionId(@RequestParam Long sessionId) {
        return ResponseHandler.handleResponse("Results by session id", sessionRecipientService.getSessionResultsBySessionId(sessionId));
    }

}
