package com.examly.springapp.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.Player;
import com.examly.springapp.entities.Team;
import com.examly.springapp.services.AdminService;

@RestController
@RequestMapping("/api/admin")

public class AdminController {
    private AdminService adminService;

    @CrossOrigin(origins = "https://8080-dffafdafedbdcbadebfefaccdcffceeecaffac.premiumproject.examly.io")
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
    	List <Player> player = adminService.getAllPlayers();
    	if(player.isEmpty())
    	{
    		throw new NoSuchElementException("Player is empty");
    	}
        return player;
    }

    @GetMapping("/Welcome")  
public String hello()   
{  
return "Welcome to SpringProject";  
} 

    // // Endpoint to create a new player
    // @PostMapping("/players")
    // public Player createPlayer(@RequestBody Player player) {
    //     return adminService.createPlayer(player);
    // }

    // Endpoint to update an existing player
    // @PutMapping("/players")
    // public Player updatePlayer(@RequestBody Player updatedPlayer) {
    //     return adminService.updatePlayer(updatedPlayer);
    // }

    // // Endpoint to delete a player by its ID
    // @DeleteMapping("/players/{playerId}")
    // public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
    //     adminService.deletePlayer(playerId);
    //     return ResponseEntity.ok("Player deleted successfully");
    // }

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @CrossOrigin(origins = "https://8080-dffafdafedbdcbadebfefaccdcffceeecaffac.premiumproject.examly.io")
    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team team) {
        return adminService.createTeam(team);
    }

    @CrossOrigin(origins = "https://8080-dffafdafedbdcbadebfefaccdcffceeecaffac.premiumproject.examly.io")
    @GetMapping("/teams")
    public List<Team> getAllTeams() {
    	List <Team> teams = adminService.getAllTeams();
    	if(teams.isEmpty())
    	{
    		throw new NoSuchElementException("Team is empty");
    	}
    	return teams;
    }

    // Endpoint to update an existing team
    @PutMapping("/teams")
    public Team updateTeam(@RequestBody Team updatedTeam) {
        return adminService.updateTeam(updatedTeam);
    }

    // Endpoint to delete a team by its ID
    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long teamId) {
        adminService.deleteTeam(teamId);
        return ResponseEntity.ok("Team deleted successfully");
    }

    // Endpoint to create a new player
    @CrossOrigin(origins = "https://8080-dffafdafedbdcbadebfefaccdcffceeecaffac.premiumproject.examly.io")
    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        return adminService.createPlayer(player);
    }

    // Endpoint to update an existing player
    @PutMapping("/players")
    public Player updatePlayer(@RequestBody Player updatedPlayer) {
        return adminService.updatePlayer(updatedPlayer);
    }

    // Endpoint to delete a player by its ID
    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
        adminService.deletePlayer(playerId);
        return ResponseEntity.ok("Player deleted successfully");
    }
}


