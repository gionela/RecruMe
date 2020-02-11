package com.gr.RecruMe.controllers;


import com.gr.RecruMe.models.Match;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MatchController {
    private MatchService matchService;

    @Autowired
    public MatchController (MatchService matchService){
        this.matchService = matchService;
        }

    @GetMapping("test/{id1}/{id2}")
//   public List<Skill> testController(@PathVariable int id1,@PathVariable int id2){
//        return matchService.compareSkillsLists(id1,id2 );
//    }
    public Match testController(@PathVariable int id1,@PathVariable int id2){
        return matchService.getAutoMatch(id1,id2);
    }

    @GetMapping("matches/auto")
    public List<Match> getAllAutoMatchesController(){
        return matchService.getAllAutoMatches();
    }
}
