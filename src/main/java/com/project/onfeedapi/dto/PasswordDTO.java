package com.project.onfeedapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
//@Data read about
public class PasswordDTO {
    private String newPassword;
    private String password;
}
