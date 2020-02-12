package com.gr.RecruMe.controllers;

import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.repositories.JobSkillRepository;
import com.gr.RecruMe.services.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {

    private ReportService reportService;

    public ReportingController(ReportService reportService){
        this.reportService = reportService;
    }
    @GetMapping
    public Skill getMostRequestedSkillController(){
        return reportService.getMostRequestedSkill();
    }
}
