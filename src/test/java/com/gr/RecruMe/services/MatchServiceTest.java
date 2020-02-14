package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.MatchDto;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.Job;
import com.gr.RecruMe.models.Match;
import com.gr.RecruMe.models.MatchStatus;
import com.gr.RecruMe.repositories.*;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //creates the ApplicationContext utilized in tests
class MatchServiceTest {
    private Applicant applicant;
    private Job job;
    private Match match;
    private MatchDto matchDto;
    private List<Match> allMatches;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    ApplicantSkillRepository applicantSkillRepository;
    @Autowired
    JobSkillRepository jobSkillRepository;
    @Autowired
    private MatchService matchService;

    @BeforeEach
    void setUp(){

        applicant = applicantRepository.findById(4).get();
        job = jobRepository.findById(3).get();
        match = new Match();
        match.setApplicant(applicant);
        match.setJob(job);
        //match.setMatchStatus(MatchStatus.MANUAL);
        matchDto = new MatchDto(3,4);
        allMatches = matchRepository.findAll();

    }

    @Test
    void saveNewManualMatch(){
     //   Hibernate.initialize(match.getApplicant());
        assertEquals(match.getApplicant().getFirstName(), matchService.saveNewManualMatch(matchDto).getApplicant().getFirstName());
    }
    @Test
    void getAllMatches() {
        assertEquals(allMatches.size(), matchService.getAllMatches().size());
    }

    @Test
    void getAllManualMatches() {
        assertEquals(2, matchService.getAllManualMatches().size()); //runs
    }

    @Test
    void getAllAutoMatches() {
        assertEquals(3, matchService.getAllAutoMatches().size());
    }

    @Test
    void getSkillsFromJobOffer() {
    }

    @Test
    void getSkillsFromApplicant() {
    }
}