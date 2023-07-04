package com.project.onfeedapi.repository;

import com.project.onfeedapi.dto.EmployeeDTO;
import com.project.onfeedapi.dto.SessionDTO;
import com.project.onfeedapi.model.Employee;
import com.project.onfeedapi.model.Form;
import com.project.onfeedapi.model.Session;
import com.project.onfeedapi.model.SessionRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRecipientsRepository extends JpaRepository<SessionRecipient, Long>, JpaSpecificationExecutor<SessionRecipient> {

    List<SessionRecipient> getAllBySessionIdOrderByUpdatedAt(Long sessionId);

    List<SessionRecipient> getAllByEmployeeId(Long employeeId);

    @Query("select count(sr.isCompleted) as answered from session_recipients sr where sr.isCompleted = true and sr.session.id = :sessionId")
    int getNumberOfAnswered(@Param("sessionId") Long sessionId);

    @Query("select sr from session_recipients sr where sr.employee.id = :employeeId")
    List<SessionRecipient> getRecipientsByEmployeeId();

    SessionRecipient getBySessionAndEmployee(Session session, Employee employee);

    SessionRecipient getByEmployee(Employee employee);
}
