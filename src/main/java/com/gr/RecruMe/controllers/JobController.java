package com.gr.RecruMe.controllers;

import com.gr.RecruMe.dtos.JobDto;
import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("jobs")
    public List<Job> getAllJobsController(){
        return jobService.getAllJobs();
    }

    @GetMapping("job/{id}")
    public Job getJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }

    @PostMapping("job")
    public Job saveNewJobController(@RequestBody JobDto jobDto) {
        return jobService.saveNewJob(jobDto);
    }

    @PutMapping("job/{id}")
    public Job updateJobController(@PathVariable int id, JobDto jobDto) {
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
    public List<Job> getJobsByDatePostedController(@PathVariable int year, @PathVariable int month, @PathVariable int day){
        return jobService.getJobsByDatePosted(year, month, day);
    }


}