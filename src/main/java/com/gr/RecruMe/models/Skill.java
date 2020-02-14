package com.gr.RecruMe.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skillName;


    @OneToMany(mappedBy = "skill") //, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonIgnore
    private List<ApplicantSkill> skillApplicantSkill;

    @OneToMany(mappedBy = "skill") //, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonIgnore
    private List<JobSkill> skillJobSkill;

    public Skill(String skillName) {
        this.skillName = skillName;
    }
}