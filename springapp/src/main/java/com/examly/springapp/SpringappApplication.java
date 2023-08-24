package com.examly.springapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("com.examly")
public class SpringappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringappApplication.class, args);

		CricketerService cricketerService = new CricketerService();
		
		ArrayList<Cricketer> cricketersList = new ArrayList<>();

        cricketersList.add(new Cricketer("Virat Kohli", 32, "India"));
        cricketersList.add(new Cricketer("Steve Smith", 31, "Australia"));
        cricketersList.add(new Cricketer("Kane Williamson", 35, "New Zealand"));
        cricketersList.add(new Cricketer("MS Dhoni", 30, "India"));


        // Add more cricketers as needed

        // Sort by name using Comparable
        Collections.sort(cricketersList);
        
       // For JDBC CONNECTION TO SAVE DATA IN DB
       for(Cricketer c: cricketersList)
       {
    	   cricketerService.addCricketer(c);
       }

        // Print the cricketers sorted by name
        System.out.println("Cricketers sorted by name:");
        for (Cricketer cricketer : cricketersList) {
            System.out.println("Name: " + cricketer.getName() +
                    ", Age: " + cricketer.getAge() +
                    ", Country: " + cricketer.getCountry());
        }

        // Sort by age using Comparator
        Collections.sort(cricketersList, new CricketerAgeComparator());

       
        
        // Print the cricketers sorted by age
        System.out.println("Cricketers sorted by age:");
        for (Cricketer cricketer : cricketersList) {
            System.out.println("Name: " + cricketer.getName() +
                    ", Age: " + cricketer.getAge() +
                    ", Country: " + cricketer.getCountry());
        }
        
        // For JDBC CONNECTION TO GET DATA FROM DB
        System.out.println("Display the Cricketers Deatails From DB");
        List <Cricketer> cricketerListDB = cricketerService.getAllCricketer();
        
        for (Cricketer cricketer : cricketerListDB) {
            System.out.println("Name: " + cricketer.getName() +
                    ", Age: " + cricketer.getAge() +
                    ", Country: " + cricketer.getCountry());
        }
        
        
        TeamsService ts  = new TeamsService();
		
		ArrayList<Teams> teamList = new ArrayList<>();
		
		teamList.add(new Teams("Senior Team", "India"));
		teamList.add(new Teams("Junior Team", "Australia"));
		teamList.add(new Teams("Senior Team", "New Zealand"));
	        
		 // For JDBC CONNECTION TO SAVE DATA IN DB
	       for(Teams t: teamList)
	       {
	    	   ts.addTeams(t);
	       }
	
	       // For JDBC CONNECTION TO GET DATA FROM DB
	       System.out.println("Display the Team Deatails From DB");
	       List <Teams> teamList1 = ts.getAllTeams();
	       
	       for (Teams t : teamList1) {
	           System.out.println("Name: " + t.getName() +
	                   ", Country: " + t.getCountry());
	       }
	       
	       // For JDBC CONNECTION TO GET DATA FROM DB from both the tables
	      ts.getCricketerWithTeam();
	}

	 
   

}
