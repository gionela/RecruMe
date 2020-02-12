package com.gr.RecruMe.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApplicantDto {
    private String firstName;
    private String lastName;
    private String address;
    private String region;
    private int educationLevel;
    private int skillLevel;
    private List<Integer> skillsFromUser;

}
