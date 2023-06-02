package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Form;
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

    @Query(value = "SELECT sr.* " +
            "FROM session_recipients sr " +
            "JOIN (SELECT s.id " +
            "      FROM sessions s " +
            "      JOIN (SELECT q.form_id " +
            "            FROM answers a " +
            "            JOIN questions q ON a.question_id = q.id " +
            "            WHERE q.id = :questionId) q1 ON q1.form_id = s.form_id) q2 ON q2.id = sr.session_id " +
            "WHERE sr.employee_id = :employeeId", nativeQuery = true)
    SessionRecipient getRecipientByQuestionAndEmployee(@Param("questionId") Long questionId, @Param("employeeId") Long employeeId);
}
