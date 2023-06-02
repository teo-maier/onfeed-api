package com.project.onfeedapi.model;

import com.project.onfeedapi.utils.EmployeeType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Formula;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity(name = "employees")
@Table(name = "employees")
public class Employee extends Identity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Formula("concat(first_name,' ', last_name)")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private boolean active;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    public Employee(String email, String password, EmployeeType employeeType) {
        this.email = email;
        this.password = password;
        this.type = employeeType;
    }

    public Employee(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
