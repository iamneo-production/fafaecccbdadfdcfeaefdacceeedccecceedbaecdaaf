package com.examly.springapp;

import java.util.List;

public class TeamsService{

	private TeamDAO teamDao;
	
	public TeamsService() {
		 this.teamDao = new TeamDAO();
		 }

	public List<Teams> getAllTeams() {
		return teamDao.getAllTeams();
	}

	public void addTeams(Teams team) {
		teamDao.addTeam(team);
	}
	
	public void getCricketerWithTeam()
	{
		teamDao.getCricketerWithTeam();
	}
}
