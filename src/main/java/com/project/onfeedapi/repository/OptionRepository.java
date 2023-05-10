package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Option;
import com.project.onfeedapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long>, JpaSpecificationExecutor<Option> {
    List<Option> findByQuestionId(Long questionId);
}
