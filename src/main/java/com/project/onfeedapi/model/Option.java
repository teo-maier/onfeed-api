package com.project.onfeedapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity(name = "options")
@Table(name = "options")
public class Option extends Identity {
    @Column(name = "value")
    private String value;

    @ManyToOne
    @MapsId("questionId")
    private Question question;
}
