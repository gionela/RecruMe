package com.gr.RecruMe.dtos;

import com.gr.RecruMe.models.ApplicantSkill;
import com.gr.RecruMe.models.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data

@AllArgsConstructor
public class ApplicantDto {
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private String address;
    private String region;
    private int educationLevel;
//    private Set<Skill>  aSkills;
//
//    public ApplicantDto(){
//        aSkills = new HashSet<>();
//    }
}
/*
    "firstName": "CCC",
            "lastName": "Marley",
            "dob": "2001-01-31T22:00:00.000+0000",
            "address": "address4",
            "region": "Athens",
            "educationLevel": "MASTER_DEGREE"

 */