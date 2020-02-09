package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.JobDto;
import com.gr.RecruMe.models.EducationLevel;
import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(int id) {
        return jobRepository.findById(id).get();
    }
    /**
     * reducing 1 from monthOfPost and adding 1 to dayOfPost to fix java's default counting on the corresponding parameter of GregorianCAlendar constructor
     * @param jobDto requires data for the new job's fields
     * @return a new job offer
     */
    public Job saveNewJob(JobDto jobDto) {
        Job job = new Job();
        job.setJobTitle(jobDto.getJobTitle());
        job.setCompanyName(jobDto.getCompanyName());
        job.setRegion(jobDto.getRegion());
        job.setEducationLevelRequired(EducationLevel.valueOf(jobDto.getEducationLevelRequired()));
        job.setPostedDate(new GregorianCalendar(jobDto.getYearOfPost(),
                jobDto.getMonthOfPost() - 1, jobDto.getDayOfPost() + 1));
        return jobRepository.save(job);
    }

    /**
     * updates the fields of an existing job if the user's input is not null on each required field
     * date of the posted job is not allowed to be updated
     * @param id     to get job
     * @param jobDto requires new data for jobs fields
     * @return the updated job
     */
    public Job updateJob(int id, JobDto jobDto) {
        Job job = jobRepository.findById(id).get();
        if ((jobDto.getJobTitle() != null))
            job.setJobTitle(jobDto.getJobTitle());
        if ((jobDto.getCompanyName() != null))
            job.setCompanyName(jobDto.getCompanyName());
        if (jobDto.getEducationLevelRequired() != 0)
            job.setEducationLevelRequired(EducationLevel.valueOf(jobDto.getEducationLevelRequired()));
        if (jobDto.getRegion() != null)
            job.setRegion(jobDto.getRegion());
        return jobRepository.save(job);
    }
    /**
     * search all jobs by region
     * @param region
     * @return all jobs of the specific region
     */
    public List<Job> getJobsByRegion(String region) {
        return jobRepository
                .findAll()
                .stream()
                .filter(job -> job.getRegion().equals(region))
                .collect(Collectors.toList());
    }

    /**
     * search all jobs by company name
     * @param companyName
     * @return all jobs by a specific company
     */
    public List<Job> getJobsByCompanyName(String companyName) {
        return jobRepository
                .findAll()
                .stream()
                .filter(job -> job.getCompanyName().equals(companyName))
                .collect(Collectors.toList());
    }
/*
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
 */

    public List<Job> getJobsByDatePosted(int year, int month, int day) {
        return jobRepository.findAll()
                .stream()
                .filter(job -> job.getPostedDate().get(Calendar.YEAR) == year)
                .filter(job -> job.getPostedDate().get(Calendar.MONTH) == month)
                .filter(job->job.getPostedDate().get(Calendar.DATE) == day)
                .collect(Collectors.toList());
    }
}