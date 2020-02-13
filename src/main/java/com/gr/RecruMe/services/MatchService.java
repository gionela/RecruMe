
package com.gr.RecruMe.services;

import com.gr.RecruMe.dtos.MatchDto;
import com.gr.RecruMe.models.*;
import com.gr.RecruMe.repositories.ApplicantRepository;
import com.gr.RecruMe.repositories.JobRepository;
import com.gr.RecruMe.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
     * finds all the manual matches from all matches
     *
     * @return a list of all manual matches
     */
    public List<Match> getAllManualMatches() { // remember to set this private if you do not eventually make controller
        return matchRepository.findAll()
                .stream()
                .filter(match -> match.getMatchStatus().equals(MatchStatus.MANUAL))
                .collect(Collectors.toList());
    }

    public List<Match> getAllAutoMatches() { // remember to set this private if you do not eventually make controller
        return matchRepository.findAll()
                .stream()
                .filter(match -> match.getMatchStatus().equals(MatchStatus.AUTO))
                .collect(Collectors.toList());
    }

    /**
     * removes a manual match from all matches
     *
     * @param id match id
     */
    public void deleteManualMatchById(int id) {
        List<Match> manualMatches = getAllManualMatches();
        Match match1 = manualMatches.stream().filter(match -> match.getId() == id).findFirst().get();
        matchRepository.deleteById(match1.getId());
    }

    /**
     * Saves a new match.
     * Sets its Status to Manual
     * Sets the respective applicant and job offer to INACTIVE
     *
     * @param matchDto get new Match Data from front end
     * @return a new Match. which is also a manual match
     */
    public Match saveNewManualMatch(MatchDto matchDto) {
        Applicant applicant = applicantRepository.findById(matchDto.getApplicantId()).get(); // exception if not exists
        Job job = jobRepository.findById(matchDto.getJobId()).get();
        Match match = new Match();
        match.setApplicant(applicant);
        match.setJob(job);
        match.setMatchStatus(MatchStatus.MANUAL);
        applicant.setActive(false);
        job.setActive(false);
        return matchRepository.save(match);
    }

    /**
     * Updates an existing match's, by id, matchStatus to Final when invoked
     * Used as business logic to provide FINALIZED functionality
     * sets the finalized date of the match to the current date and time
     * Used as an update example.
     *
     * @param matchId match id
     * @return updated Match object (row in database)
     */
    public Match updateManualToFinal(int matchId) {
        Match match = matchRepository.findById(matchId).get();
        match.setMatchStatus(MatchStatus.FINAL);
        match.setDateFinalized(Calendar.getInstance());
        matchRepository.save(match);
        return match;
    }

    public List<Match> updateAllAutoToFinal() {
        List<Match> autoToFinal = new ArrayList<>();
        matchRepository.findAll().stream().filter(match -> match.getMatchStatus().equals(MatchStatus.AUTO))
                .forEach(match -> {
                    match.setMatchStatus(MatchStatus.FINAL);
                    match.setDateFinalized(Calendar.getInstance());
                    matchRepository.save(match);
                    autoToFinal.add(match);
                });
        return autoToFinal;
    }

    /**
     * gets a list of the required skills from a registered job offer
     *
     * @param jobId job id
     * @return this job's required skills
     */

    private List<Skill> getSkillsFromJobOffer(int jobId) {
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
    private List<Skill> getSkillsFromApplicant(int applicantId) {
        List<Skill> skills = new ArrayList<>();
        Applicant applicant = applicantRepository.findById(applicantId).get();
        applicant.getApplicantSkills().forEach(applicantSkill -> skills.add(applicantSkill.getSkill()));
        return skills;
    }


    /**
     * checks all job offers
     *
     * @return al list of those that are matched by an applicant
     */
    public List<Match> createAllAutoMatches() {
        List<Match> automaticMatches = new ArrayList<>();
        List<Job> availableJobs = jobRepository.findAll() //EXTRACT METHOD HERE
                .stream()
                .filter(job -> job.isActive()) //get all active jobs
                .collect(Collectors.toList());
        List<Applicant> availableApplicants = applicantRepository.findAll() //EXTRACT METHOD HERE
                .stream()
                .filter(applicant -> applicant.isActive()) //get all active applicants
                .collect(Collectors.toList());
        for (Job job : availableJobs) { // foreach available job
            for (Applicant applicant : availableApplicants) { // foreach available applicant
//check if the skill list of the applicant contains all the skill from the job offer skill list. if yes return true
                if (getSkillsFromApplicant(applicant.getId()).containsAll(getSkillsFromJobOffer(job.getId()))) {
                    Match match = new Match();
                    job.setActive(false);
                    applicant.setActive(false);
                    match.setMatchStatus(MatchStatus.AUTO);
                    match.setApplicant(applicant);
                    match.setJob(job);
                    match = matchRepository.save(match);
                    automaticMatches.add(match);
                    break;
                }
            }
        }
        return automaticMatches; // THROW EXCEPTION IF NONE MATCHED
    }

    ///////////////// APO EDW KAI KATW FTIAXNEIS NEO AUTO MATCH /////////////////////////////////////////////////////////
//    /**
//     * Takes the list of skills of one job offer and the list of skills of an applicant and compares them
//     *
//     * @param jobId       job offer id
//     * @param applicantId applicant id
//     * @return a list of all matched skills
//     * returns empty list if no match
//     */
//    private List<Skill> compareSkillsLists(int jobId, int applicantId) {
//        List<Skill> matchedSkills = new ArrayList<>();
//        List<Skill> skillsFromJobList = getSkillsFromJobOffer(jobId);
//        List<Skill> skillsFromJobApplicant = getSkillsFromApplicant(applicantId);
//        for (Skill sj : skillsFromJobList) {
//            for (Skill sa : skillsFromJobApplicant)
//                if (sj.getId() == sa.getId()) {
//                    matchedSkills.add(sj);
//                    break; // go to next job skill
//                }
//        }
//        return matchedSkills; // EXCEPTION WHEN THIS LIST IS EMPTY (NO MATCH)
//    }
//
//    /**
//     * Checks if automatic match occured/ If comparison evaluates to true
//     * Compares job offer skill list size with matched skills list from applicant skills
//     *
//     * @param jobId       job offer id
//     * @param applicantId applicant id
//     * @return true if the two lists have the same list
//     */
//    private boolean isAutoMatch(int jobId, int applicantId) {
//        List<Skill> autoMatchSkills = compareSkillsLists(jobId, applicantId);
//        List<Skill> skillsFromJobOffer = getSkillsFromJobOffer(jobId);
//        if (autoMatchSkills.size() == skillsFromJobOffer.size()) ;
//        {
//            return true; // you do not care if false the below method has to handle this
//        }
//    }
//
//    /**
//     * Creates a Match object if automatic match between a job offer and an applicant has occured
//     * Sets this Match's matchStatus =  AUTO since it is also an automatic match
//     *
//     * @param jobId       job offer id
//     * @param applicantId applicant id
//     * @return a Match object. which is also an automatic match.
//     */
//
//    public Match getAutoMatch(int jobId, int applicantId) { // Don't forget to set this to private if you wont use it outside
//        Job job = jobRepository.findById(jobId).get();//EXCEPTION IF JOB NOT NOT EXISTS
//        Applicant applicant = applicantRepository.findById(applicantId).get();//EXCEPTION IF APPLICANT NOT NOT EXISTS
//        Match match = new Match();
//        boolean isAutoMatch = isAutoMatch(jobId, applicantId);
//        if (isAutoMatch) { // DO NOTHING. DO YOU NEED A MESSAGE/EXCEPTIONS HERE?
//            job.setActive(false);
//            applicant.setActive(false);
//            match.setApplicant(applicant);
//            match.setJob(job);
//            match.setMatchStatus(MatchStatus.AUTO);
//            // DON'T FORGET TO SET finalizedDate WHEN CRITERIA IS MATCHED
//            matchRepository.save(match);
//        }
//        return match;
//    }
    ///////////////////////////////// EDW TELEIWNEI TO E N A AUTO MATCH ////////////////////////////////////////////////

}