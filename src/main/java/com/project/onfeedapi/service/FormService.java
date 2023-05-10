package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.FormDTO;
import com.project.onfeedapi.dto.QuestionDTO;
import com.project.onfeedapi.dto.exception.ExceptionDTO;
import com.project.onfeedapi.mapper.FormMapper;
import com.project.onfeedapi.mapper.QuestionMapper;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.model.Form;
import com.project.onfeedapi.model.Question;
import com.project.onfeedapi.repository.FormRepository;
import com.project.onfeedapi.utils.exception.OnfeedExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;

    private final QuestionService questionService;


    public List<FormDTO> getAll() {
        return formRepository.findAll().stream().map(FormMapper::convertToDTO).toList();
    }

    public Form getById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new ExceptionDTO("Could not find form with this id."));
    }


    public FormDTO create(FormDTO formDTO) {
        Form form = FormMapper.convertToModel(formDTO);
        Form savedForm = formRepository.save(form);
        formDTO.setId(savedForm.getId());
        List<Question> questionList = setFormToQuestions(formDTO, savedForm);
        questionService.create(questionList.stream().map(QuestionMapper::convertToDTO).toList());
        return formDTO;
    }

    private static List<Question> setFormToQuestions(FormDTO formDTO, Form form) {
        List<Question> questionList = formDTO.getQuestions().stream().map(QuestionMapper::convertToModel).toList();
        for (Question question : questionList) {
            question.setForm(form);
        }
        return questionList;
    }

    public FormDTO edit(Long formId, FormDTO editedForm) {
        Form form = getById(formId);
        form.setTitle(editedForm.getTitle());
        form.setDescription(editedForm.getDescription());
        Form savedForm = formRepository.save(form);
        List<Question> questionList = setFormToQuestions(editedForm, savedForm);
        questionService.edit(questionList.stream().map(QuestionMapper::convertToDTO).toList(), formId);
        return editedForm;

    }

}
