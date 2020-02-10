package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Integer> {
}
