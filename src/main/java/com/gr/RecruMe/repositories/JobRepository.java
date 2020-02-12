package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByIsActive(int number);


}