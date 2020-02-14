package com.gr.RecruMe.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data transfer object to request data input for new job offer registration
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private String jobTitle;
    private String companyName;
    private String region;
    private int yearOfPost;
    private int monthOfPost;
    private int dayOfPost;
    private int educationLevelRequired;
    private int skillLevelRequired;
    private List<Integer> skillsFromJobOffer;
}