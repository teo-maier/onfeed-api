package com.project.onfeedapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class TeamMemberId implements Serializable {
    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "teamId")
    private Long teamId;

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, teamId);
    }
}
