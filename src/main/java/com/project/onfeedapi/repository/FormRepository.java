package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface FormRepository extends JpaRepository<Form, Long>, JpaSpecificationExecutor<Form> {
    @Query(value = "select s.form from session_recipients sr join sessions s on sr.session.id = s.id " +
            "where sr.id = :recipientId")
    Form getFormByRecipientId(Long recipientId);
}
