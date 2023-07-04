package com.project.onfeedapi.service;

import com.project.onfeedapi.dto.FormDTO;
import com.project.onfeedapi.dto.exception.ExceptionDTO;
import com.project.onfeedapi.mapper.FormMapper;
import com.project.onfeedapi.mapper.QuestionMapper;
import com.project.onfeedapi.model.Form;
import com.project.onfeedapi.model.Option;
import com.project.onfeedapi.model.Question;
import com.project.onfeedapi.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final QuestionService questionService;

    public List<FormDTO> getAll() {
        return formRepository.findAll().stream().map(FormMapper::convertToDTO).toList();
    }

    public FormDTO getById(Long id) {
        return FormMapper.convertToDTO(formRepository.findById(id)
                .orElseThrow(() -> new ExceptionDTO("Could not find form with this id.", HttpStatus.NOT_FOUND)));
    }

    public void existsById(Long id) {
        if (!formRepository.existsById(id)) {
            throw new ExceptionDTO("Could not find form with this id.", HttpStatus.NOT_FOUND);
        }
    }

    public FormDTO create(FormDTO formDTO) {
        Form form = FormMapper.convertToModel(formDTO);
        questionService.setFormToQuestions(formDTO, form);
        formRepository.save(form);
        return FormMapper.convertToDTO(formRepository.save(form));
    }


    public FormDTO edit(Long formId, FormDTO editedForm) {
        FormDTO form = getById(formId);
        form.setTitle(editedForm.getTitle());
        form.setDescription(editedForm.getDescription());
        form.setQuestions(editedForm.getQuestions());
        Form formModel = FormMapper.convertToModel(form);
        // update options
        formModel.setQuestions(editedForm.getQuestions().stream().map(QuestionMapper::convertToModelWithOptions).toList());
        for (Question question : formModel.getQuestions()) {
            for (Option option : question.getOptions()) {
                option.setQuestion(question);
            }
            question.setForm(formModel);
        }
        formRepository.save(formModel);
        return form;
    }

    public FormDTO getFormByRecipientId(Long recipientId) {
        return FormMapper.convertToDTO(formRepository.getFormByRecipientId(recipientId));
    }

    public void delete(Long formId) {
        if (formRepository.existsById(formId)) {
            formRepository.deleteById(formId);
        } else {
            throw new ExceptionDTO("Could not find form by id", HttpStatus.NOT_FOUND);
        }
    }

}
