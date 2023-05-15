package com.project.onfeedapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "team_members")
@Table(name = "team_members")
public class TeamMember {
    @EmbeddedId
    private TeamMemberId id;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    @ManyToOne
    @MapsId("teamId")
    private Team team;

    @Column(name = "is_manager")
    private boolean manager;

    @Override
    public int hashCode() {
        return Objects.hash(getId().getTeamId(), getId().getEmployeeId());
    }
}
