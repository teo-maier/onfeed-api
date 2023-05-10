package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OptionRepository extends JpaRepository<Option, Long>, JpaSpecificationExecutor<Option> {
}
