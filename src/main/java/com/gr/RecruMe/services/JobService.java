package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.JobDto;
import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}