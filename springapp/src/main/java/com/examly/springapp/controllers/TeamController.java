package com.examly.springapp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.Teams;
import com.examly.springapp.services.TeamService;

@RestController
public class TeamController {

    @Autowired
    private TeamService service;
    
    @GetMapping("/team/getAllList")
    private List <Teams> getallTeam()
    {
        return service.getAllTeamsByList();
    }
    
//    @GetMapping("/team/getByJpa")
//    private List <Teams> getallJPATeam()
//    {
//        return repo.findAll();
//    }
    
  }
 