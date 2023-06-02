package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.SessionDTO;
import com.project.onfeedapi.dto.SessionRecipientDTO;
import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.service.SessionRecipientService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("session-recipients")
public class SessionRecipientsController {

    private final SessionRecipientService sessionRecipientService;

    @GetMapping("/{recipientId}")
    public ResponseEntity<AbstractResponseDTO> getById(@PathVariable Long recipientId) {
        return ResponseHandler.handleResponse("Get recipient by session id", sessionRecipientService.getById(recipientId));
    }

    @GetMapping
    public ResponseEntity<AbstractResponseDTO> getAllBySessionId(@RequestParam("sessionId") Long sessionId) {
        return ResponseHandler.handleResponse("Get all recipients by session id", sessionRecipientService.getAllBySessionId(sessionId));
    }

    @GetMapping("/all-by-employee")
    public ResponseEntity<AbstractResponseDTO> getAllByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        return ResponseHandler.handleResponse("Get all recipients by employee id", sessionRecipientService.getAllByEmployeeId(employeeId));
    }

    @GetMapping("/results")
    public ResponseEntity<AbstractResponseDTO> getSessionResultBySessionId(@RequestParam Long sessionId) {
        return ResponseHandler.handleResponse("Results by session id", sessionRecipientService.getSessionResultsBySessionId(sessionId));
    }


    @PutMapping("/{recipientId}")
    public ResponseEntity<AbstractResponseDTO> edit(@PathVariable Long recipientId, @RequestBody SessionRecipientDTO editedSessionRecipient) {
        return ResponseHandler.handleResponse("Recipient edited successfully", sessionRecipientService.edit(recipientId, editedSessionRecipient));
    }


}
