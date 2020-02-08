package com.gr.RecruMe.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class JobSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn
    private Job job;
    @ManyToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)//delete also from all connecting tables
    private Skill skill;
}