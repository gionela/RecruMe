package com.gr.RecruMe.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data transfer object to request data input for new applicant
 */
@Data
@NoArgsConstructor
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
    private int skillLevel;
    private List<Integer> skillsFromUser;

}
/*
    "firstName": "CCC",
            "lastName": "Marley",
            "dob": "2001-01-31T22:00:00.000+0000",
            "address": "address4",
            "region": "Athens",
            "educationLevel": "MASTER_DEGREE"

 */