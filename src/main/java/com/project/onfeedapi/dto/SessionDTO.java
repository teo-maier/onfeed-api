package com.project.onfeedapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDTO {

    private Long id;

    @Nullable
    private String title;

    @Nullable
    private String description;

    @Nullable
    private FormDTO form;

    @Nullable
    private EmployeeDTO creator;

    @Nullable
    private List<SessionRecipientDTO> sessionRecipients;

    private boolean isAnonymous;

    private boolean isSuggestion;

    private boolean isDraft;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
