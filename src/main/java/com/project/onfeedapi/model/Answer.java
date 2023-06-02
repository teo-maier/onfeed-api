package com.project.onfeedapi.model;

import com.project.onfeedapi.dto.EmployeeDTO;
import com.project.onfeedapi.dto.QuestionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity(name = "answers")
@Table(name = "answers")
public class Answer extends Identity {
    @Column(name = "value")
    private String value;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionAnswer> options = new ArrayList<>();

    public void addOption(OptionAnswer option) {
        options.add(option);
        option.setAnswer(this);
    }

    public void removeOption(OptionAnswer option) {
        options.remove(option);
        option.setAnswer(null);
    }
}
