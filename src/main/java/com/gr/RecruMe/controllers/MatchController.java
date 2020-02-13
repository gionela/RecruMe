package com.gr.RecruMe.controllers;


import com.gr.RecruMe.dtos.MatchDto;
import com.gr.RecruMe.models.Match;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.services.MatchService;
import com.gr.RecruMe.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recrume")
public class MatchController {
    private MatchService matchService;
    private SkillService skillService;

    @Autowired
    public MatchController(MatchService matchService, SkillService skillService) {

        this.matchService = matchService;
        this.skillService = skillService;
    }

       @GetMapping("test/")
//   public List<Skill> testController(@PathVariable int id1,@PathVariable int id2){
//        return matchService.compareSkillsLists(id1,id2 );
//    }
//    public Match testController(@PathVariable int id1, @PathVariable int id2) {
//        return matchService.getAutoMatch(id1, id2);
//    }
       public List<Skill> testController(){
        return matchService.getSkillsFromJobOffer(1);
       }

    /**
     * get all matches
     *
     * @return a list of all saved matches in db
     */
    @GetMapping("matches")
    public List<Match> getAllMatchesController() {
        return matchService.getAllMatches();
    }

    /**
     * get all manual matches
     *
     * @return a list of all saved manual matches in db
     */
    @GetMapping("matches/manual")
    public List<Match> getAllManualMatchesController() {
        return matchService.getAllManualMatches();
    }

    /**
     * get all automatic matches
     *
     * @return a list of all saved automatic matches in db
     */
    @GetMapping("matches/auto")
    public List<Match> getAllAutoMatchesController() {
        return matchService.getAllAutoMatches();
    }

    /**
     * Saves new Manual Match
     *
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
     *
     * @param matchId match id
     * @return updated Match object (row in database)
     */
    @PutMapping("match/manual/finalize/{matchId}")
    public Match updateManualToFinalController(@PathVariable int matchId) {
        return matchService.updateManualToFinal(matchId);
    }

    @PutMapping("matches/auto/finalize")
    public List<Match> updateAllAutoToFinalController() {
        return matchService.updateAllAutoToFinal();
    }

    /**
     * deletes a manual match based on a given id
     *
     * @param id manual match id
     */
    @DeleteMapping("manualMatches/delete/{id}")
    public void deleteManualMatchByIdController(@PathVariable int id) {
        matchService.deleteManualMatchById(id);
    }


}
