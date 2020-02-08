package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.EducationLevel;
import com.gr.RecruMe.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public Applicant getApplicant(int id) {
        return applicantRepository.findById(id).get();
    }

    /**
     * reducing 1 from monthOfBirth and adding 1 to dayOfBirth to fix java's default counting on the corresponding parameter of GregorianCAlendar constructor
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
}
