package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.EducationLevel;
import com.gr.RecruMe.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

    public Applicant saveNewApplicant(ApplicantDto applicantDto){
        Applicant applicant = new Applicant();
        applicant.setFirstName(applicantDto.getFirstName());
        applicant.setLastName(applicantDto.getLastName());
        applicant.setAddress(applicantDto.getAddress());
        applicant.setRegion(applicantDto.getRegion());
        applicant.setEducationLevel(EducationLevel.valueOf(applicantDto.getEducationLevel()));
        applicant.setDob(new GregorianCalendar(applicantDto.getYearOfBirth(),applicantDto.getMonthOfBirth(),applicantDto.getDayOfBirth()));
        return applicantRepository.save(applicant);
    }


}
