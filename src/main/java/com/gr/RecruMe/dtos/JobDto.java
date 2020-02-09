package com.gr.RecruMe.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}