package com.project.onfeedapi.model;


import com.project.onfeedapi.utils.AnswerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity(name = "questions")
@Table(name = "questions")
public class Question extends Identity {
    @Column(name = "value")
    private String value;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @ManyToOne()
    @JoinColumn(name = "form_id")
    private Form form;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Option> options;
}
