package com.gr.RecruMe.dtos;

import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantSkillDto {
    private int applicantId;
    private List<SkillDto> skills;
}
