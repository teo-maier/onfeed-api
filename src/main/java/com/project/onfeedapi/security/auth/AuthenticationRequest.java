package com.project.onfeedapi.security.auth;

import com.project.onfeedapi.utils.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String firstName;
    private String lastName;
    private String email;
    String password;
    EmployeeType type;
}