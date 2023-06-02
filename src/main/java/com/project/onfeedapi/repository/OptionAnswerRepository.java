package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.OptionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OptionAnswerRepository extends JpaRepository<OptionAnswer, Long>, JpaSpecificationExecutor<OptionAnswer> {
}
