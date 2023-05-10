package com.project.onfeedapi.model;


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
@Entity(name = "forms")
@Table(name = "forms")
public class Form extends Identity {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "form", orphanRemoval = true)
//    private List<Question> questions;
}
