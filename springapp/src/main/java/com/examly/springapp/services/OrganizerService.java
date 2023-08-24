package com.examly.springapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Player;
import com.examly.springapp.entities.Team;
import com.examly.springapp.exceptions.ExceedsTeamBudgetException;
import com.examly.springapp.exceptions.PlayerAlreadyAssignedException;
import com.examly.springapp.repositories.PlayerRepository;
import com.examly.springapp.repositories.TeamRepository;

@Service
public class OrganizerService {
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    @Autowired
    public OrganizerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    // Method to fetch unsold players by category
    public List<Player> getUnsoldPlayersByCategory(String category) {
        return playerRepository.findByCategoryAndSoldFalse(category);
    }
    
 // Method to fetch unsold players
    public List<Player> getUnsoldPlayers() {
        return playerRepository.findBySoldFalse();
    }
    
    public List<Player> getsoldPlayers() {
        return playerRepository.findBySoldTrue();
    }

    // Method to assign an unsold player to a team
    public void assignPlayerToTeam(Long playerId, Long teamId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new NoSuchElementException("Player not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("Team not found"));

        if(player.isSold())
        {
        	throw new PlayerAlreadyAssignedException("Player already mapped");
        }
        double playerBiddingPrice = player.getBiddingPrice();
        double teamBudget = team.getMaximumBudget();

        if (playerBiddingPrice <= teamBudget) {
            player.setSold(true);
            player.setTeam(team);
            playerRepository.save(player);

            // Trigger email to the player
//            emailService.sendPlayerAssignmentEmail(player.getEmail(), player.getName(), team.getName());
        } else {
            throw new ExceedsTeamBudgetException("Exceeds team budget");
        }
    }

    // Method to release a player from a team
    public void releasePlayerFromTeam(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new NoSuchElementException("Player not found"));

        if (player.getTeam() != null) {
            player.setTeam(null);
            player.setSold(false);
            playerRepository.save(player);
        } else {
            throw new NoSuchElementException("Player is not assigned to any team");
        }
    }
    
   public List<Player> getPlayerList(Long teamId)
   {
	   List<Player> playerList = new ArrayList <Player>();
	   playerList=playerRepository.findByTeamId(teamId);
	   return playerList;
   }
}

