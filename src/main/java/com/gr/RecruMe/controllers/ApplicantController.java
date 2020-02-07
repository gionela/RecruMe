package com.gr.RecruMe.controllers;

import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
