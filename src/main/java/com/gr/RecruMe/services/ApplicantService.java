package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.dtos.ApplicantSkillDto;
import com.gr.RecruMe.dtos.UpdateApplicantDto;
import com.gr.RecruMe.models.*;
import com.gr.RecruMe.repositories.ApplicantRepository;
import com.gr.RecruMe.repositories.ApplicantSkillRepository;
import com.gr.RecruMe.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    private ApplicantRepository applicantRepository;
    private SkillRepository skillRepository;
    private ApplicantSkillRepository applicantSkillRepository;

    /**
     * set dependencies  in class's  constructor
     *
     * @param applicantRepository
     * @param skillRepository
     * @param applicantSkillRepository
     */
    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository,
                            SkillRepository skillRepository,
                            ApplicantSkillRepository applicantSkillRepository) {
        this.applicantRepository = applicantRepository;
        this.skillRepository = skillRepository;
        this.applicantSkillRepository = applicantSkillRepository;
    }


    /**
     * finds all registered applicants in database
     *
     * @return a list of all applicants registered
     */
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    /**
     * finds a registered applicant in database
     *
     * @param id applicant's id
     * @return the corresponding applicant
     */
    public Applicant getApplicantById(int id) { //EXCEPTION IF NOT EXISTS
        return applicantRepository.findById(id).get();
    }

    /**
     * reducing 1 from monthOfBirth and adding 1 to dayOfBirth to fix java's default counting on the corresponding parameter of GregorianCalendar constructor
     *
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
        applicant.setSkillLevel(SkillLevel.valueOf(applicantDto.getSkillLevel()));
        applicant.setDob(new GregorianCalendar(applicantDto.getYearOfBirth(), applicantDto.getMonthOfBirth() - 1, applicantDto.getDayOfBirth() + 1));
        applicant.setApplicantSkills(new ArrayList<>());
        applicantRepository.save(applicant);

        for (Integer asdto : applicantDto.getSkillsFromUser()) {
            Skill skill = skillRepository.findById(asdto).get();
            ApplicantSkill applicantSkill = new ApplicantSkill();
            applicantSkill.setApplicant(applicant);
            applicantSkill.setSkill(skill);

            applicantSkillRepository.save(applicantSkill);
            applicant.getApplicantSkills().add(applicantSkill);
        }
        applicantRepository.save(applicant);
        return applicant;
    }


    /**
     * updates the fields of an existing applicant if the user input is not null on each required field
     * birthday is not allowed to be updated
     * the fields of firstName and lastName are updated only of they are null in the existing applicant
     *
     * @param id           to get applicant
     * @param updateApplicantDto requires new data for applicant fields
     * @return the updated applicant
     */
    public Applicant updateApplicant(int id, UpdateApplicantDto updateApplicantDto) { // EXCEPTION IF NOT EXISTS
        Applicant applicant = applicantRepository.findById(id).get();
        if ((updateApplicantDto.getFirstName() != null) && (applicant.getFirstName() != null))
            applicant.setFirstName(updateApplicantDto.getFirstName());
        if ((updateApplicantDto.getLastName() != null) && (applicant.getFirstName() != null))
            applicant.setLastName(updateApplicantDto.getLastName());
        if (updateApplicantDto.getEducationLevel() != 0)
            applicant.setEducationLevel(EducationLevel.valueOf(updateApplicantDto.getEducationLevel()));
        if (updateApplicantDto.getRegion() != null)
            applicant.setRegion(updateApplicantDto.getRegion());
        if (updateApplicantDto.getAddress() != null)
            applicant.setAddress(updateApplicantDto.getAddress());
        if (updateApplicantDto.getSkillLevel() != 0)
            applicant.setSkillLevel(SkillLevel.valueOf(updateApplicantDto.getSkillLevel()));
        return applicantRepository.save(applicant);
    }

    /**
     * search all applicants by region
     *
     * @param region required
     * @return all applicants of the specific region
     */
    public List<Applicant> getApplicantsByRegion(String region) {
        return applicantRepository
                .findAll()
                .stream()
                .filter(applicant -> applicant.getRegion().equals(region))
                .collect(Collectors.toList());
    }

    /**
     * User enters the below parameters
     *
     * @param firstName
     * @param lastName
     * @return the applicants with the required first names and last names
     */
    public List<Applicant> getApplicantsByName(String firstName, String lastName) { //EXCEPTION IF NOT EXISTS
        return applicantRepository
                .findAll()
                .stream()
                .filter(applicant -> applicant.getFirstName().equals(firstName) && applicant.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    /**
     * gets all applicants within a given age range
     *
     * @param ageFrom older than
     * @param ageTo   younger than
     * @return a list of the corresponding applicants
     */
    public List<Applicant> getApplicantByAgeRange(int ageFrom, int ageTo) { // EXCEPTION IF NOT EXISTS
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);// save current year to an integer variable
        int yearTo = thisYear - ageFrom; //calculate the corresponding year from the user's ageFrom input eg:2000 if ageFrom = 20
        int yearFrom = thisYear - ageTo; //calculate the corresponding year from the user's ageTo input eg:1980 if ageFrom = 40
        return applicantRepository
                .findAll()
                .stream()
                .filter(applicant -> applicant.getDob().get(Calendar.YEAR) >= yearFrom) //get all applicants younger than 40 years old
                .filter(applicant -> applicant.getDob().get(Calendar.YEAR) <= yearTo) //get all applicants older than 20 years old
                .collect(Collectors.toList());
    }

    public List<Applicant> getAllApplicantsBySkill(int skillId) { //EXCEPTION IF SKILL DOES NOT EXIST
        List<ApplicantSkill> as = applicantSkillRepository.findAll()
                .stream().filter(applicantSkill -> applicantSkill.getSkill().getId() == skillId).collect(Collectors.toList());

        List<Applicant> applicantsThatHaveTheSkill = new ArrayList<>();
        for (ApplicantSkill a : as) {
            applicantsThatHaveTheSkill.add(a.getApplicant());
        }
        return applicantsThatHaveTheSkill;
    }

//    /**
//     * sets applicant inactive (soft delete)
//     * @param id applicant id
//     * @return updated applicant instance
//     */
//    public Applicant softDeleteApplicant(int id) {
//        Applicant applicant = applicantRepository.findById(id).get();
//        if (applicant == null)
//            return null;
//        applicant.setActive(false);
//        return applicantRepository.save(applicant);
//    }

}
