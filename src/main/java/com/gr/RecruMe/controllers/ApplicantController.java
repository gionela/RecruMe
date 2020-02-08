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

    @PostMapping("applicant")
    public Applicant saveNewApplicantController(@RequestBody ApplicantDto applicantDto) {
        return applicantService.saveNewApplicant(applicantDto);
    }
    @PutMapping("applicant/{id}")
    public Applicant updateApplicantController(@PathVariable int id, ApplicantDto applicantDto){
        return applicantService.updateApplicant(id,applicantDto);
    }

    @GetMapping("applicants/region/{region}")
    public List<Applicant> getApplicantsByRegionController(@PathVariable String region){
        return applicantService.getApplicantsByRegion(region);
    }

    @GetMapping("applicants/firstName/{firstName}/lastname/{lastName}")
    public List<Applicant> getApplicantsByNameController(@PathVariable String firstName, @PathVariable String lastName){
        return applicantService.getApplicantsByName(firstName,lastName);
    }

}
