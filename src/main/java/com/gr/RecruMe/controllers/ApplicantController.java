package com.gr.RecruMe.controllers;

import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;
    @GetMapping("applicants")
    public List<Applicant> getAllApplicantsController(){
        return applicantService.getAllApplicants();
    }

    @GetMapping("applicant/{id}")
    public Applicant getApplicantController(@PathVariable int id) {
        return applicantService.getApplicant(id);
    }

    @PostMapping("/applicant")
    public Applicant saveNewApplicantController(@RequestBody ApplicantDto applicantDto) {
        return applicantService.saveNewApplicant(applicantDto);
    }

}
