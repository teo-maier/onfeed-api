package com.project.onfeedapi.controller;

import com.project.onfeedapi.dto.FormDTO;
import com.project.onfeedapi.dto.response.AbstractResponseDTO;
import com.project.onfeedapi.service.FormService;
import com.project.onfeedapi.utils.response.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("form")
public class FormController {
    private final FormService formService;

    @GetMapping("/all")
    public ResponseEntity<AbstractResponseDTO> getAll() {
        return ResponseHandler.handleResponse("All forms", formService.getAll());
    }

    @GetMapping("/{formId}")
    public ResponseEntity<AbstractResponseDTO> getById(@PathVariable Long formId) {
        return ResponseHandler.handleResponse("Get form by id", formService.getById(formId));
    }

    @PostMapping
    public ResponseEntity<AbstractResponseDTO> create(@RequestBody FormDTO formDTO) {
        return ResponseHandler.handleResponse("Form created successfully", formService.create(formDTO));
    }

    @PutMapping("/{formId}")
    public ResponseEntity<AbstractResponseDTO> edit(@PathVariable Long formId, @RequestBody FormDTO editedForm) {
        return ResponseHandler.handleResponse("User created successfully", formService.edit(formId, editedForm));
    }

}
