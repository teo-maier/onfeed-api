package com.project.onfeedapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.onfeedapi.utils.EmployeeType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    private boolean active;

    private EmployeeType type;
}
