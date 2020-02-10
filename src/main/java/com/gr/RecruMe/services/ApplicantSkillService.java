package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.dtos.ApplicantSkillDto;
import com.gr.RecruMe.repositories.ApplicantRepository;
import com.gr.RecruMe.repositories.ApplicantSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantSkillService {

    private ApplicantSkillRepository applicantSkillRepository;
    private ApplicantRepository applicantRepository;
    private ApplicantService applicantService;
    @Autowired
    public ApplicantSkillService(ApplicantSkillRepository applicantSkillRepository,
                                 ApplicantRepository applicantRepository,
                                 ApplicantService applicantService){
        this.applicantSkillRepository = applicantSkillRepository;
        this.applicantRepository = applicantRepository;
        this.applicantService = applicantService;
    }

//    public ApplicantSkill (ApplicantSkillDto applicantSkillDto){
//        applicantService.saveNewApplicant(ApplicantDto applicantDto)
//
//    }
}
