package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.service.QuestionService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/all/{formId}")
    public ResponseEntity<AbstractResponseDTO> getAllByFormId(@PathVariable Long formId) {
        return ResponseHandler.handleResponse("All questions by form id", questionService.getAllByFormId(formId));
    }
}
