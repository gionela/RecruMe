package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.ApplicantSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, Integer> {

    @Query(value = "select TOP 4 skill_id\n" +
            "    FROM applicant_skill\n" +
            "    GROUP BY (skill_id)\n" +
            "    ORDER BY COUNT (skill_id) DESC ", nativeQuery = true)
    List<Integer> getMostOfferedSkills();

}
