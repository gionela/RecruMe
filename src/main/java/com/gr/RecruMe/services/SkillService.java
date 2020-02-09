package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.SkillDto;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.models.SkillLevel;
import com.gr.RecruMe.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(int id) {
        return skillRepository.findById(id).get();
    }

    /**
     * @param skillDto required data for the new skill's fields
     * @return add skill
     */
    public Skill saveNewSkill(SkillDto skillDto) {
        Skill skill = new Skill();
        skill.setSkillName(skillDto.getSkillName());
        skill.setSkillLevel(SkillLevel.valueOf(skillDto.getSkillLevel()));

        return skillRepository.save(skill);
    }

    /**
     * @param id       get skill
     * @param skillDto required data for the new skill's fields
     * @return updated skill
     */
    public Skill updateSkill(int id, SkillDto skillDto) {
        Skill skill = skillRepository.findById(id).get();
        if ((skillDto.getSkillName() != null))
            skill.setSkillName(skillDto.getSkillName());
        if (skillDto.getSkillLevel() != 0)
            skill.setSkillLevel(SkillLevel.valueOf(skillDto.getSkillLevel()));
        return skillRepository.save(skill);
    }

    public void deleteSkillById(int id) {
        skillRepository.deleteById(id);
    }
}