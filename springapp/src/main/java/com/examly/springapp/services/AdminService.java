package com.examly.springapp.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Player;
import com.examly.springapp.entities.Team;
import com.examly.springapp.repositories.PlayerRepository;
import com.examly.springapp.repositories.TeamRepository;

@Service
public class AdminService {
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;


    @Autowired
    public AdminService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }
    
    // Method to create a new team
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    // Method to update an existing team
    public Team updateTeam(Team updatedTeam) {
        Team existingTeam = teamRepository.findById(updatedTeam.getId())
                .orElseThrow(() -> new NoSuchElementException("Team not found"));
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setMaximumBudget(updatedTeam.getMaximumBudget());
        return teamRepository.save(existingTeam);
    }

    // Method to delete a team by its ID
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    // Method to create a new player
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    // Method to update an existing player
    public Player updatePlayer(Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(updatedPlayer.getId())
                .orElseThrow(() -> new NoSuchElementException("Player not found"));
        existingPlayer.setName(updatedPlayer.getName());
        existingPlayer.setAge(updatedPlayer.getAge());
        existingPlayer.setCategory(updatedPlayer.getCategory());
        existingPlayer.setBiddingPrice(updatedPlayer.getBiddingPrice());
        return playerRepository.save(existingPlayer);
    }

    // Method to delete a player by its ID
    public void deletePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
    }
    
    public List<Player> getAllPlayers() {
    	List <Player> playerList = playerRepository.findAll();
        return playerList;
    }
    
    public List<Team> getAllTeams() {
    	List <Team> teamList = teamRepository.findAll();
        return teamList;
    }
    
    public List<Team> getAllTeamsByJPA()
    {
        return teamRepository.findAll();
    }
    

}

