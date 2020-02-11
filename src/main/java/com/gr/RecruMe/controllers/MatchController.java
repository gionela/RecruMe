package com.gr.RecruMe.controllers;


import com.gr.RecruMe.dtos.ApplicantDto;
import com.gr.RecruMe.dtos.MatchDto;
import com.gr.RecruMe.models.Applicant;
import com.gr.RecruMe.models.Match;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MatchController {
    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("test/{id1}/{id2}")
//   public List<Skill> testController(@PathVariable int id1,@PathVariable int id2){
//        return matchService.compareSkillsLists(id1,id2 );
//    }
    public Match testController(@PathVariable int id1, @PathVariable int id2) {
        return matchService.getAutoMatch(id1, id2);
    }

    @GetMapping("matches/auto")
    public List<Match> getAllAutoMatchesController() {
        return matchService.getAllAutoMatches();
    }

    /**
     * Saves new Manual Match
     * @param matchDto contains frontend data for new match's fields
     * @return save new match
     */
    @PostMapping("match")
    public Match saveNewManualMatchController(@RequestBody MatchDto matchDto) {
        return matchService.saveNewManualMatch(matchDto);
    }

    /**
     * Updates an existing match and only allows the matchStatus field to be updated.
     * Used as business logic to provide FINALIZED functionality
     * Used as an update example.
     * @param id match id
     * @param matchDto get data from front end
     * @return updated Match object (row in database)
     */
    @PutMapping("match/finalize/{id}")
    public Match updateMatchToFinalController(@PathVariable int id, MatchDto matchDto) {
        return matchService.updateMatchToFinal(id, matchDto);
    }
    /**
     * deletes a manual match based on a given id
     * @param id manual match id
     */
    @DeleteMapping("manualMatches/delete/{id}")
    public void deleteManualMatchByIdController(@PathVariable int id) {
        matchService.deleteMatchById(id);
    }


}
