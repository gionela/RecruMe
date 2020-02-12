package com.gr.RecruMe.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Data
//@NoArgsConstructor
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
    private EducationLevel educationLevel;
    private SkillLevel skillLevel;
    @Column(columnDefinition = "bit default 1")
    private boolean isActive;
//,fetch = FetchType.EAGER,cascade=CascadeType.ALL
    @OneToMany(mappedBy = "applicant" )
    @JsonIgnore
    private List<ApplicantSkill> applicantSkills;

    /**
     * when created always active
      */
    public  Applicant(){
        isActive = true;
    }



}
