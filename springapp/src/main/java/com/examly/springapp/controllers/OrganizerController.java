package com.examly.springapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.Player;
import com.examly.springapp.services.OrganizerService;

@RestController
@RequestMapping("/api/organizer")
@CrossOrigin(origins = "https://8081-dffafdafedbdcbadebfefaccdcffceeecaffac.premiumproject.examly.io")
public class OrganizerController {
    private OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

//    // Endpoint to fetch unsold players by category
//    @GetMapping("/unsold-players")
//    public List<Player> getUnsoldPlayersByCategory(@RequestParam String category) {
//        return organizerService.getUnsoldPlayersByCategory(category);
//    }

    // Endpoint to assign an unsold player to a team
    @PostMapping("/assign-player")
    public ResponseEntity<Boolean> assignPlayerToTeam(@RequestParam Long playerId, @RequestParam Long teamId) {
        organizerService.assignPlayerToTeam(playerId, teamId);
        return ResponseEntity.ok(true);
    }

    // Endpoint to release a player from a team
    @PutMapping("/release-player")
    public ResponseEntity<String> releasePlayerFromTeam(@RequestParam Long playerId) {
        organizerService.releasePlayerFromTeam(playerId);
        return ResponseEntity.ok("Player released from the team successfully");
    }
    
    @GetMapping("/unsold-players")
    public List<Player> getUnsoldPlayersByCategory() {
        return organizerService.getUnsoldPlayers();
    }
    
    @GetMapping("/sold-players")
    public List<Player> getsoldPlayersByCategory() {
        return organizerService.getsoldPlayers();
    }
    
    @GetMapping("/player-list")
    public List<Player> getPlayerList(@RequestParam Long teamId) {
        return organizerService.getPlayerList(teamId);
    }
  
}


