package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Integer> {

    @Query(value = "SELECT id\n" +
            "FROM skill as s\n" +
            "where (s.id NOt in (SELECT DISTINCT skill_id from applicant_skill)) ", nativeQuery = true)
    List<Integer> getNotMatchedByAppSkills();

    Skill findFirstBySkillName(String skillName);
}