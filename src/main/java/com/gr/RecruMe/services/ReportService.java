package com.gr.RecruMe.services;

import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.repositories.JobRepository;
import com.gr.RecruMe.repositories.JobSkillRepository;
import com.gr.RecruMe.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReportService {
    private JobSkillRepository jobSkillRepository;
    private SkillRepository skillRepository;
@Autowired
    public ReportService(JobSkillRepository jobSkillRepository,SkillRepository skillRepository){
        this.jobSkillRepository = jobSkillRepository;
        this.skillRepository = skillRepository;
    }

    public Skill getMostRequestedSkill(){
    return skillRepository.findById(jobSkillRepository.getMostRequestedSkill()).get();
    }
}
