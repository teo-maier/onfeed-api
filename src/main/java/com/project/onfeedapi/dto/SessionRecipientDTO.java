package com.project.onfeedapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionRecipientDTO {

    private Long id;

    private SessionDTO session;

    private EmployeeDTO employee;

    private boolean isCompleted;
}
