package com.gr.RecruMe.controllers;

import com.gr.RecruMe.dataimports.ImportApplicantService;
import com.gr.RecruMe.dataimports.ImportJobOfferService;
import com.gr.RecruMe.dataimports.ImportSkillService;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ImportController {


    private ImportApplicantService importApplicantService;
    private ImportSkillService importSkillService;
    private ImportJobOfferService importJobOfferService;
    @Autowired
    public ImportController(ImportApplicantService importApplicantService,
                            ImportSkillService importSkillService,
                            ImportJobOfferService importJobOfferService){
        this.importApplicantService = importApplicantService;
        this.importSkillService = importSkillService;
        this.importJobOfferService = importJobOfferService;

    }

    @GetMapping("excel/skills")
    public List<Skill> ImportSkillFromExcel() throws IOException {
        return importSkillService.ImportSkillFromExcel();
    }

    @GetMapping("excel/applicants")
    public List<Applicant> ImportApplicantFromExcel() throws IOException {
        return importApplicantService.ImportApplicantFromExcel();
    }

    @GetMapping("excel/jobs")
    public List<Job> ImportJobFromExcel() throws IOException {
        return importJobOfferService.ImportJobFromExcel();
    }

}

