package com.gr.RecruMe.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Calendar dob;
    private String address;
    private String region;
    EducationLevel educationLevel;

    @OneToMany //(cascade=CascadeType.ALL)
    @JoinColumn
    private Set<ApplicantSkill> applicantSkills;

}
