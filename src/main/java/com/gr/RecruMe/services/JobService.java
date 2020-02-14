package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.JobDto;
import com.gr.RecruMe.exceptions.ErrorMessage;
import com.gr.RecruMe.exceptions.NotFoundException;
import com.gr.RecruMe.models.*;
import com.gr.RecruMe.repositories.JobRepository;
import com.gr.RecruMe.repositories.JobSkillRepository;
import com.gr.RecruMe.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private JobRepository jobRepository;
    private SkillRepository skillRepository;
    private JobSkillRepository jobSkillRepository;

    @Autowired
    public JobService(JobRepository jobRepository, SkillRepository skillRepository, JobSkillRepository jobSkillRepository){
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.jobSkillRepository = jobSkillRepository;
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public Job getJobById(int id) throws NotFoundException {
        Job job = jobRepository.findById(id).orElse(null);
        if(job == null){
            throw new NotFoundException(ErrorMessage.JOB_NOT_FOUND + " id = " + id);
        }
        return job;
    }
    /**
     * reducing 1 from monthOfPost and adding 1 to dayOfPost to fix java's default counting on the corresponding parameter of GregorianCAlendar constructor
     * @param jobDto requires data for the new job's fields
     * @return a new job offer
     */
    public Job saveNewJob(JobDto jobDto) { // exctract methods from here...too long
        Job job = new Job();
        job.setJobTitle(jobDto.getJobTitle());
        job.setCompanyName(jobDto.getCompanyName());
        job.setRegion(jobDto.getRegion());
        job.setEducationLevelRequired(EducationLevel.valueOf(jobDto.getEducationLevelRequired()));
        job.setSkillLevelRequired(SkillLevel.valueOf(jobDto.getSkillLevelRequired()));
        job.setPostedDate(new GregorianCalendar(jobDto.getYearOfPost(), jobDto.getMonthOfPost() - 1, jobDto.getDayOfPost() + 1));
        job.setJobSkills(new ArrayList<>());
        jobRepository.save(job);
        for(Integer jsDto : jobDto.getSkillsFromJobOffer()){
            Skill skill = skillRepository.findById(jsDto).get();
            JobSkill jobSkill = new JobSkill();
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkillRepository.save(jobSkill);
            job.getJobSkills().add(jobSkill);
        }
        jobRepository.save(job);
        return job;
    }

    /**
     * updates the fields of an existing job if the user's input is not null on each required field
     * date of the posted job is not allowed to be updated
     * @param id     to get job
     * @param jobDto requires new data for jobs fields
     * @return the updated job
     */
    public Job updateJob(int id, JobDto jobDto) throws NotFoundException{
        Job job = jobRepository.findById(id).orElse(null);
        if(job == null){
            throw new NotFoundException(ErrorMessage.JOB_NOT_FOUND);
        }
        if ((jobDto.getJobTitle() != null))
            job.setJobTitle(jobDto.getJobTitle());
        if ((jobDto.getCompanyName() != null))
            job.setCompanyName(jobDto.getCompanyName());
        if (jobDto.getEducationLevelRequired() != 0)
            job.setEducationLevelRequired(EducationLevel.valueOf(jobDto.getEducationLevelRequired()));
        if (jobDto.getSkillLevelRequired() != 0)
            job.setSkillLevelRequired(SkillLevel.valueOf(jobDto.getSkillLevelRequired()));
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

    /**
     * user inserts the following parameters as integers
     * @param year
     * @param month
     * @param day
     * @return a list of all jobs posted on the required date
     */
    public List<Job> getJobsByDatePosted(int year, int month, int day) throws NotFoundException {
        List<Job> jobsByPostedDate = jobRepository.findAll()
                                                    .stream()
                                                    .filter(job -> job.getPostedDate().get(Calendar.YEAR) == year)
                                                    .filter(job -> job.getPostedDate().get(Calendar.MONTH) == (month-1))
                                                    .filter(job->job.getPostedDate().get(Calendar.DATE) == day)
                                                    .collect(Collectors.toList());
        if (jobsByPostedDate.isEmpty()){
            throw new NotFoundException(ErrorMessage.NO_JOB_IN_DATE);
        }
        return jobsByPostedDate;
    }
    /**
     * sets job inactive (soft delete)
     * @param id job id
     * @return updated job instance/ made him unavailable
     */
    public Job softDeleteJob(int id) {
        Job job = jobRepository.findById(id).get();
        if (job == null)
            return null;
        job.setActive(false);
        return jobRepository.save(job);
    }

}
