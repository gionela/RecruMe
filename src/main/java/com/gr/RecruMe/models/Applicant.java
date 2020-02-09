package com.gr.RecruMe.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Data
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

    @OneToMany(fetch = FetchType.EAGER) //(cascade=CascadeType.ALL)
    @JoinColumn
    private List<ApplicantSkill> applicantSkills;

    public Applicant(){
        applicantSkills = new ArrayList<>();
    }

}
