package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.ApplicantSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, Integer> {
}
