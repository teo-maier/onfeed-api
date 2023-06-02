package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.AnswerListDTO;
import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.service.AnswerService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("answer")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/question")
    public ResponseEntity<AbstractResponseDTO> getByQuestionIdAndEmployeeId(@RequestParam Long questionId, @RequestParam Long employeeId) {
        return ResponseHandler.handleResponse("Get answer by question id", answerService.getByQuestionId(questionId, employeeId));
    }

    @PostMapping
    public ResponseEntity<AbstractResponseDTO> create(@RequestBody AnswerListDTO answerListDTO) {
        return ResponseHandler.handleResponse("Answers created successfully", answerService.create(answerListDTO));
    }
}
