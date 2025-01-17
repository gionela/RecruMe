package com.gr.RecruMe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jobTitle;
    private String companyName;
    private String region;
    private Calendar postedDate;
    private EducationLevel educationLevelRequired;
    private SkillLevel skillLevelRequired;
    @Column(columnDefinition = "bit default 1")
    private boolean isActive;

    @OneToMany(mappedBy = "job" )
    @JsonIgnore
    private List<JobSkill> jobSkills;

    /**
     * when created always active till set other
     */
    public Job(){
        isActive = true;
    }

}