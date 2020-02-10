package com.gr.RecruMe.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Calendar dateFinalized;
    private MatchStatus matchStatus;

    @OneToOne //(cascade = CascadeType.ALL)
    private Applicant applicant;

    @OneToOne  //(cascade = CascadeType.ALL)
    private Job job;


}