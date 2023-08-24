package com.examly.springapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.Teams;

@Service
public class TeamService {
    private List<Teams> teams;

    public TeamService() {
        // Initialize with some hardcoded teams for demonstration purposes
        teams = new ArrayList<>();
        teams.add(new Teams("Team A", "India"));
        teams.add(new Teams("Team B", "India"));
        teams.add(new Teams("Team C", "India"));
    }

    public List<Teams> getAllTeamsByList() {
        return teams;
    }
}
