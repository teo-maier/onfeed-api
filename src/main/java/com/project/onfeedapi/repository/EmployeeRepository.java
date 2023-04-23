package com.project.onfeedapi.repository;

import com.project.onfeedapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    Employee findByEmail(String email);

    Optional<Employee> findOptionalByEmail(String email);

    @Query("select e.active from employees e where e.id = :id")
    boolean getStatusById(@Param("id") long id);

    @Modifying()
    @Query("update employees e set e.active = :status where e.id = :id")
    void toggleEmployeeStatus(@Param("id") long id, @Param("status") boolean status);
}
