package com.gr.RecruMe.controllers;

import com.gr.RecruMe.models.Match;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.repositories.JobSkillRepository;
import com.gr.RecruMe.services.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("recrume/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }
    @GetMapping("mostRequired")
    public List<Skill> getMostRequestedSkillController(){
        return reportService.getMostRequestedSkills();
    }
    @GetMapping("mostOffered")
    public List<Skill> getMostOfferedSkillController(){
        return reportService.getMostOfferedSkills();
    }
    @GetMapping("notMatched")
    public List<Skill> getNotMatchedByAppSkillsController(){
        return reportService.getNotMatchedByAppSkills();
    }
    @GetMapping("reports/recentlyFinalized")
    public List<Match>getRecentlyFinalizedController(){
        return reportService.getRecentlyFinalized();
    }
}
