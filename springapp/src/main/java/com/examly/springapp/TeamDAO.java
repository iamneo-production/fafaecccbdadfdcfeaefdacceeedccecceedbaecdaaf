package com.examly.springapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TeamDAO {

	Connection conn = null;
	 PreparedStatement stmt = null;
	 ResultSet rs = null;
	public List<Teams> getAllTeams() {
	 List<Teams> teamList = new ArrayList<>();
	 
	 try {
	 conn = JDBCUtils.getConnection();
	// System.out.println("naveen"+conn);
	 stmt = conn.prepareStatement("SELECT * FROM teams");
	 rs = stmt.executeQuery();
	 while (rs.next()) {
	 
	 String name = rs.getString("name");
	 String country = rs.getString("country");
	 
	 Teams team = new Teams(name, country);
	 teamList.add(team);
	 }
	 } catch (SQLException e) {
	 e.printStackTrace();
	 } finally {
	 JDBCUtils.closeResultSet(rs);
	 JDBCUtils.closeStatement(stmt);
	 JDBCUtils.closeConnection(conn);
	 }
	 return teamList;
	 }
	public void addTeam(Teams team) {
	 try {
	 conn = JDBCUtils.getConnection();
	 
	 PreparedStatement stmt = conn.prepareStatement("INSERT INTO teams (name, country) VALUES (?, ?)"); 
	 {
	 stmt.setString(1, team.getName());
	 stmt.setString(2, team.getCountry());
	 stmt.executeUpdate();
	 }
	 } catch (SQLException e) {
	 e.printStackTrace();
	 }
	 finally {
	 JDBCUtils.closeResultSet(rs);
	 JDBCUtils.closeStatement(stmt);
	 JDBCUtils.closeConnection(conn);
	 }
	} 
	
	public void getCricketerWithTeam() {
		 
		 try {
		 conn = JDBCUtils.getConnection();
		 
		 String sql = "SELECT c.name AS cricketer_name, c.country AS cricketer_country, " +
                 "t.name AS team_name, t.country AS team_country " +
                 "FROM cricketer c " +
                 "INNER JOIN teams t ON c.country = t.country";
		 PreparedStatement preparedStatement = conn.prepareStatement(sql);

         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
             String cricketerName = resultSet.getString("cricketer_name");
             String cricketerCountry = resultSet.getString("cricketer_country");
             String teamName = resultSet.getString("team_name");
             String teamCountry = resultSet.getString("team_country");

             System.out.println("Cricketer: " + cricketerName);
             System.out.println("Cricketer Country: " + cricketerCountry);
             System.out.println("Team: " + teamName);
             System.out.println("Team Country: " + teamCountry);
             System.out.println("----------------------------------");
         }
		 } catch (SQLException e) {
		 e.printStackTrace();
		 } finally {
		 JDBCUtils.closeResultSet(rs);
		 JDBCUtils.closeStatement(stmt);
		 JDBCUtils.closeConnection(conn);
		 }
		 }
}
