package com.project.onfeedapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

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

    private boolean isReviewed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
