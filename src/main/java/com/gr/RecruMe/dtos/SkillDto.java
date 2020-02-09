package com.gr.RecruMe.dtos;
import com.gr.RecruMe.models.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {
    private String skillName;
    private int skillLevel;
}