package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long>, JpaSpecificationExecutor<Session> {

    @Query(value = "select s, sr.isCompleted from sessions s join session_recipients sr on sr.session.id = s.id " +
            "where sr.employee.id = :employeeId AND sr.isCompleted = false")
    List<Session> getAllNotCompletedByEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value = "select s, sr.isCompleted from sessions s join session_recipients sr on sr.session.id = s.id " +
            "where sr.employee.id = :employeeId AND sr.isCompleted = true")
    List<Session> getAllCompletedByEmployeeId(@Param("employeeId") Long employeeId);
}
