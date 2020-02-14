package com.gr.RecruMe.controllers;

import com.gr.RecruMe.dtos.SkillDto;
import com.gr.RecruMe.models.Skill;
import com.gr.RecruMe.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to send/receive data for all Skill associated services
 */

@RestController
@RequestMapping("recrume")
public class SkillController {

    private SkillService skillService;
    @Autowired
    public  SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    @GetMapping("skills")
    public List<Skill> getAllSkillsController() {
        return skillService.getAllSkills();
    }

    @GetMapping("skills/{id}")
    public Skill getSkillByIdController(@PathVariable int id) {
        return skillService.getSkillById(id);
    }

    @PostMapping("skills")
    public Skill saveNewSkillController(@RequestBody SkillDto skillDto) {
        return skillService.saveNewSkill(skillDto);
    }

    @PutMapping("skills/{id}")
    public Skill updateSkillController(@PathVariable int id, SkillDto skillDto) {
        return skillService.updateSkill(id, skillDto);
    }

//    @DeleteMapping("skill/{id}")
//    public void deleteSkillByIdController(@PathVariable int id) {
//        skillService.deleteSkillById(id);
//    }
}