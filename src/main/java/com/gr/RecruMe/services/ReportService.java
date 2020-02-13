package com.gr.RecruMe.services;

import com.gr.RecruMe.models.Match;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReportService {
    private JobSkillRepository jobSkillRepository;
    private SkillRepository skillRepository;
    private ApplicantSkillRepository applicantSkillRepository;
    private MatchRepository matchRepository;

    @Autowired
    public ReportService(JobSkillRepository jobSkillRepository, SkillRepository skillRepository,
                         ApplicantSkillRepository applicantSkillRepository,MatchRepository matchRepository) {
        this.jobSkillRepository = jobSkillRepository;
        this.skillRepository = skillRepository;
        this.applicantSkillRepository = applicantSkillRepository;
        this.matchRepository = matchRepository;
    }
/////////////////// REMEMBER TO MAKE THESE ALL GENERIC - they are the same method///////////////////////////////////
    /**
     * Retrieve the list of the 20 most requested skills.
     * @return
     */
    public List<Skill> getMostRequestedSkills() {
        List<Skill> mostRequested = new ArrayList<>();
        List<Integer> mostRequestedIds = jobSkillRepository.getMostRequestedSkills();
        for (int i : mostRequestedIds) {
            mostRequested.add(skillRepository.findById(i).get());
        }
        return mostRequested;
    }

    /**
     * Retrieve the list of the 20 most offered skills.
     * @return
     */
    public List<Skill> getMostOfferedSkills() {
        List<Skill> mostOffered = new ArrayList<>();
        List<Integer> mostOfferedIds = applicantSkillRepository.getMostOfferedSkills();
        for (int i : mostOfferedIds) {
            mostOffered.add(skillRepository.findById(i).get());
        }
        return mostOffered;
    }

    /**
     * Retrieve the list of the not matched skills by the applicants
     * @return
     */
    public List<Skill> getNotMatchedByAppSkills() {
        List<Skill> notMatched = new ArrayList<>();
        List<Integer> notMatchedIds = skillRepository.getNotMatchedByAppSkills();
        for (int i : notMatchedIds) {
            notMatched.add(skillRepository.findById(i).get());
        }
        return notMatched;
    }

    /**
     * Retrieve the list of the 20 recent finalized matches applicants-offers
     * @return
     */
    public List<Match> getRecentlyFinalized(){
        List<Match> recentFinals = new ArrayList<>();
        List<Integer> recentFinalIds = matchRepository.getRecentlyFinalized();
        for (int i : recentFinalIds) {
            recentFinals.add(matchRepository.findById(i).get());
        }
        return recentFinals;
    }
}
