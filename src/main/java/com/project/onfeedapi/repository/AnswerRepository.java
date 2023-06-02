package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

    Answer getByQuestionIdAndEmployeeId(Long questionId, Long employeeId);
}
