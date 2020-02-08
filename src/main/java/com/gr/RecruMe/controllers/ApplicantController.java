package com.gr.RecruMe.controllers;

import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.dtos.ApplicantSkillDto;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.ApplicantSkill;
import com.gr.RecruMe.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping("applicants")
    public List<Applicant> getAllApplicantsController() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("applicant/{id}")
    public Applicant getApplicantByController(@PathVariable int id) {
        return applicantService.getApplicantById(id);
    }

    @PostMapping("applicant")
    public Applicant saveNewApplicantController(@RequestBody ApplicantDto applicantDto) {
        return applicantService.saveNewApplicant(applicantDto);
    }

    @PutMapping("applicant/{id}")
    public Applicant updateApplicantController(@PathVariable int id, ApplicantDto applicantDto) {
        return applicantService.updateApplicant(id, applicantDto);
    }

    @GetMapping("applicants/region/{region}")
    public List<Applicant> getApplicantsByRegionController(@PathVariable String region) {
        return applicantService.getApplicantsByRegion(region);
    }

    /**
     * User enters the below parameters
     * @param firstName
     * @param lastName
     * @return the applicants with the required first names and last names
     */
    @GetMapping("applicants/firstName/{firstName}/lastname/{lastName}")
    public List<Applicant> getApplicantsByNameController(@PathVariable String firstName, @PathVariable String lastName) {
        return applicantService.getApplicantsByName(firstName, lastName);
    }

    /**
     * gets all applicants within a given age range
     * @param ageFrom older than
     * @param ageTo   younger than
     * @return a list of the corresponding applicants
     */
    @GetMapping("applicants/ageFrom/{ageFrom}/ageTo/{ageTo}")
    public List<Applicant> getApplicantsByAgeRange(@PathVariable int ageFrom, @PathVariable int ageTo) {
        return applicantService.getApplicantByAgeRange(ageFrom, ageTo);
    }
//    @PostMapping("applicantmeskills")
//    public ApplicantSkill saveNewApplicantSkill(@RequestBody ApplicantSkillDto applicantSkillDto){
//       return applicantService.saveNewApplicantSkill(applicantSkillDto);
//    }

}
