package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.JobSkill;
import com.gr.RecruMe.models.Skill;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Integer> {
    @Query(value ="    select TOP 1 skill_id\n" +
            "  FROM job_skill\n" +
            "  GROUP BY (skill_id)\n" +
            "  ORDER BY COUNT (skill_id) DESC ", nativeQuery = true)
    Integer getMostRequestedSkill();
}
