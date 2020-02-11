
package com.gr.RecruMe.services;

import com.gr.RecruMe.models.*;
import com.gr.RecruMe.repositories.ApplicantRepository;
import com.gr.RecruMe.repositories.JobRepository;
import com.gr.RecruMe.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private MatchRepository matchRepository;
    private JobRepository jobRepository;
    private ApplicantRepository applicantRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, JobRepository jobRepository, ApplicantRepository applicantRepository) {
        this.matchRepository = matchRepository;
        this.jobRepository = jobRepository;
        this.applicantRepository = applicantRepository;
    }

    /**
     * finds all registered matches in database
     *
     * @return a list of all applicants registered
     */
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    /**
     * gets a list of the required skills from a registered job offer
     *
     * @param jobId job id
     * @return this job's required skills
     */
    public List<Skill> getSkillsFromJobOffer(int jobId) {
        List<Skill> skills = new ArrayList<>();
        Job job = jobRepository.findById(jobId).get();
        job.getJobSkills().forEach(jobSkill -> skills.add(jobSkill.getSkill()));
        return skills;
    }

    /**
     * gets a list of the required skills from a registered applicant
     *
     * @param applicantId applicant id
     * @return this applicant's required skills
     */
    public List<Skill> getSkillsFromApplicant(int applicantId) {
        List<Skill> skills = new ArrayList<>();
        Applicant applicant = applicantRepository.findById(applicantId).get();
        applicant.getApplicantSkills().forEach(applicantSkill -> skills.add(applicantSkill.getSkill()));
        return skills;
    }

    /**
     * Takes the list of skills of one job offer and the list of skills of an applicant and compares them
     *
     * @param jobId       job offer id
     * @param applicantId applicant id
     * @return a list of all matched skills
     * returns empty list if no match
     */
    public List<Skill> compareSkillsLists(int jobId, int applicantId) {
        List<Skill> matchedSkills = new ArrayList<>();
        List<Skill> skillsFromJobList = getSkillsFromJobOffer(jobId);
        List<Skill> skillsFromJobApplicant = getSkillsFromApplicant(applicantId);
        for (Skill sj : skillsFromJobList) {
            for (Skill sa : skillsFromJobApplicant)
                if (sj.getId() == sa.getId()) {
                    matchedSkills.add(sj);
                }
        }
        return matchedSkills;
    }

    public boolean isAutoMatch(int jobId, int applicantId) {
        List<Skill> autoMatchSkills = compareSkillsLists(jobId, applicantId);
        List<Skill> skillsFromJobOffer = getSkillsFromJobOffer(jobId);
        if (autoMatchSkills.size() == skillsFromJobOffer.size()) ;
        {
            return true;
        }
    }

    public Match getAutoMatch(int jobId, int applicantId) {
        Job job = jobRepository.findById(jobId).get();
        Applicant applicant = applicantRepository.findById(applicantId).get();
        Match match = new Match();
        boolean isAutoMatch = isAutoMatch(jobId, applicantId);
        if (isAutoMatch) {
            job.setActive(false);
            applicant.setActive(false);
            match.setApplicant(applicant);
            match.setJob(job);
            matchRepository.save(match);
            match.setMatchStatus(MatchStatus.AUTO);
        }
        return match;
    }

    public List<Match> getAllAutoMatches() {
        List<Job> allJobs = jobRepository.findAll().stream().collect(Collectors.toList());// kane oti exeis stin vasi mia lista
        List<Applicant> allApplicants = applicantRepository.findAll().stream().collect(Collectors.toList());
        List<Match> allAutoMatches = new ArrayList<>();

        for (Job j : allJobs) {
            int jobId = j.getId();
            for (Applicant a : allApplicants) {
                int applicantId = a.getId();


                if (isAutoMatch(jobId, applicantId)) { // if is not throw exception
                    allAutoMatches.add(getAutoMatch(jobId, applicantId));
                }
            }
        }
        return allAutoMatches;
    }
}