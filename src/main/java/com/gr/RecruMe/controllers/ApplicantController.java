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

    private ApplicantService applicantService;

    /**
     * set dependencies  in class's  constructor
     * @param applicantService the used service
     */
    @Autowired
    public ApplicantController(ApplicantService applicantService){
        this.applicantService = applicantService;

    }
    /**
     * end point that finds all registered applicants in database
     * @return a list of all applicants registered
     */
    @GetMapping("applicants")
    public List<Applicant> getAllApplicantsController() {
        return applicantService.getAllApplicants();
    }
    /**
     * finds a registered applicant in database
     * @param id provided from front end
     * @return the corresponding applicant. error if he does not ex
     */
    @GetMapping("applicant/{id}")
    public Applicant getApplicantByController(@PathVariable int id) {
        return applicantService.getApplicantById(id);
    }

    /**
     *
     * @param applicantDto contains data from endpoint user (frontend) for the new applicant's fields
     * @return requires data for the new applicant's fields
     */
    @PostMapping("applicant")
    public Applicant saveNewApplicantController(@RequestBody ApplicantDto applicantDto) {
        return applicantService.saveNewApplicant(applicantDto);
    }
    /**
     * updates the fields of an existing applicant if the user input is not null on each required field
     * @param id to get applicant
     * @param applicantDto requires new data for applicant fields
     * @return the updated applicant
     */
    @PutMapping("applicant/{id}")
    public Applicant updateApplicantController(@PathVariable int id, ApplicantDto applicantDto) {
        return applicantService.updateApplicant(id, applicantDto);
    }
    /**
     * search all applicants by region
     * @param region required from end point user
     * @return all applicants of the specific region
     */

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
    @GetMapping("applicants/firstName/{firstName}/lastName/{lastName}")
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
    public List<Applicant> getApplicantsByAgeRangeController(@PathVariable int ageFrom, @PathVariable int ageTo) {
        return applicantService.getApplicantByAgeRange(ageFrom, ageTo);
    }

    @GetMapping("applicants/skill/{skillId}")
    public List<Applicant> getApplicantsBySkillController(@PathVariable int skillId){ //dwse ton kwdiko tou skill
        return applicantService.getAllApplicantsBySkill(skillId);
    }

}
