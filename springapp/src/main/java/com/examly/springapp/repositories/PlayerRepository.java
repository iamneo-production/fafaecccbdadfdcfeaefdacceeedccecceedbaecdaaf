package com.examly.springapp.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByCategoryAndSoldFalse(String category);
    List<Player> findBySoldFalse();
    List<Player> findByTeamId(Long teamId);
    List<Player> findBySoldTrue();

}
