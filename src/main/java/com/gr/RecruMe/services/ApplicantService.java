package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.dtos.ApplicantSkillDto;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.ApplicantSkill;
import com.gr.RecruMe.models.EducationLevel;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.repositories.ApplicantRepository;
import com.gr.RecruMe.repositories.ApplicantSkillRepository;
import com.gr.RecruMe.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ApplicantSkillRepository applicantSkillRepository;

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public Applicant getApplicantById(int id) {
        return applicantRepository.findById(id).get();
    }

    /**
     * reducing 1 from monthOfBirth and adding 1 to dayOfBirth to fix java's default counting on the corresponding parameter of GregorianCalendar constructor
     * @param applicantDto requires data for the new applicant's fields
     * @return a new applicant
     */
    public Applicant saveNewApplicant(ApplicantDto applicantDto) {
        Applicant applicant = new Applicant();
        applicant.setFirstName(applicantDto.getFirstName());
        applicant.setLastName(applicantDto.getLastName());
        applicant.setAddress(applicantDto.getAddress());
        applicant.setRegion(applicantDto.getRegion());
        applicant.setEducationLevel(EducationLevel.valueOf(applicantDto.getEducationLevel()));
        applicant.setDob(new GregorianCalendar(applicantDto.getYearOfBirth() , applicantDto.getMonthOfBirth()-1, applicantDto.getDayOfBirth()+1));
        List<ApplicantSkill> as = new ArrayList<>();
//        for (ApplicantSkill a: as){
//            a.setApplicant(applicant);
//            for(Skill s: applicantDto.getNewApplicantSkills()){
//                a.setSkill(s);
//            }
//            as.add(a);
//        }
        for(Skill s: applicantDto.getNewApplicantSkills()){
            for (ApplicantSkill a: as){
                a.setApplicant(applicant);
                a.setSkill(s);
                as.add(a);
            }
        }
//
//            }

        applicant.setApplicantSkills(as);
        return applicantRepository.save(applicant);
    }

    /**
     * updates the fields of an existing applicant if the user input is not null on each required field
     * birthday is not allowed to be updated
     * the fields of firstName and lastName are updated only of they are null in the existing applicant
     * therefore firstName and lastName and birthday if entered are not allowed to be updated
     * @param id to get applicant
     * @param applicantDto requires new data for applicant fields
     * @return the updated applicant
     */
    public Applicant updateApplicant(int id, ApplicantDto applicantDto) {
        Applicant applicant = applicantRepository.findById(id).get();
        if ((applicantDto.getFirstName() != null) && (applicant.getFirstName() != null))
            applicant.setFirstName(applicantDto.getFirstName());
        if ((applicantDto.getLastName() != null) && (applicant.getFirstName() != null))
            applicant.setLastName(applicantDto.getLastName());
        if (applicantDto.getEducationLevel() != 0)
            applicant.setEducationLevel(EducationLevel.valueOf(applicantDto.getEducationLevel()));
        if (applicantDto.getRegion() != null)
            applicant.setRegion(applicantDto.getRegion());
        if (applicantDto.getAddress() != null)
            applicant.setAddress(applicantDto.getAddress());
        return applicantRepository.save(applicant);
    }

    /**
     * search all applicants by region
     * @param region
     * @return all applicants of the specific region
     */
    public List<Applicant> getApplicantsByRegion(String region) {
        return applicantRepository
                .findAll()
                .stream()
                .filter(applicant -> applicant.getRegion().equals(region))
                .collect(Collectors.toList());
    }

    public List<Applicant> getApplicantsByName(String firstName, String lastName) {
        return applicantRepository
                .findAll()
                .stream()
                .filter(applicant -> applicant.getFirstName().equals(firstName)&&applicant.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    /**
     * gets all applicants within a given age range
     * @param ageFrom older than
     * @param ageTo younger than
     * @return a list of the corresponding applicants
     */
    public List<Applicant> getApplicantByAgeRange(int ageFrom, int ageTo) {
        int thisYear= Calendar.getInstance().get(Calendar.YEAR);// save current year to an integer variable
        int yearTo= thisYear - ageFrom ; //calculate the corresponding year from the user's ageFrom input eg:2000 if ageFrom = 20
        int yearFrom  = thisYear - ageTo; //calculate the corresponding year from the user's ageTo input eg:1980 if ageFrom = 40
       return applicantRepository
               .findAll()
               .stream()
               .filter(applicant -> applicant.getDob().get(Calendar.YEAR)>=yearFrom) //get all applicants younger than 40 years old
               .filter(applicant -> applicant.getDob().get(Calendar.YEAR)<=yearTo) //get all applicants older than 20 years old
               .collect(Collectors.toList());
    }

//    public ApplicantSkill saveNewApplicantSkill(ApplicantSkillDto applicantSkillDto){
//        Applicant applicant = applicantRepository.findById(applicantSkillDto.getApplicantId()).get();
//        ApplicantSkill as = new ApplicantSkill();
//        applicantSkillDto.getSkills().forEach(skill -> {
//            as.setApplicant(applicant);
//            as.setSkill(skill);
//        });
//       return as;

}
