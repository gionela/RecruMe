package com.gr.RecruMe.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ApplicantSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Applicant applicant;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)//delete also from all connecting tables
    private Skill skill;
}