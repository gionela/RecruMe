package com.gr.RecruMe.controllers;

import com.gr.RecruMe.dtos.JobDto;
import com.gr.RecruMe.exceptions.NotFoundException;
import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to send/receive data for all Job offers associated services
 */

@RestController
@RequestMapping("recrume")
public class JobController {
    private JobService jobService;
    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("jobs")
    public List<Job> getAllJobsController(){
        return jobService.getAllJobs();
    }

    @GetMapping("job/{id}")
    public Job getJobById(@PathVariable int id) throws NotFoundException {
        return jobService.getJobById(id);
    }

    @PostMapping("job")
    public Job saveNewJobController(@RequestBody JobDto jobDto) {
        return jobService.saveNewJob(jobDto);
    }

    @PutMapping("job/{id}")
    public Job updateJobController(@PathVariable int id, JobDto jobDto) throws NotFoundException {
        return jobService.updateJob(id, jobDto);
    }

    @GetMapping("job/region/{region}")
    public List<Job> getJobsByRegionController(@PathVariable String region) {
        return jobService.getJobsByRegion(region);
    }

    @GetMapping("job/companyName/{companyName}")
    public List<Job> getJobsByCompanyController(@PathVariable String companyName) {
        return jobService.getJobsByCompanyName(companyName);
    }
    @GetMapping("jobs/year/{year}/month/{month}/day/{day}")
    public List<Job> getJobsByDatePostedController(@PathVariable int year, @PathVariable int month, @PathVariable int day) throws NotFoundException {
        return jobService.getJobsByDatePosted(year, month, day);
    }

}