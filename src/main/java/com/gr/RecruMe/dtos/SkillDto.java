package com.gr.RecruMe.dtos;
import com.gr.RecruMe.models.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object to request data input for new skill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {
    private String skillName;
}