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
@Entity(name = "options_answer")
@Table(name = "options_answer")
public class OptionAnswer extends Identity {
    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;
}
