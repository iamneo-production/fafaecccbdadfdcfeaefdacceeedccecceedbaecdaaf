package com.examly.springapp.entities;



import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String category;
    private double biddingPrice;
    private boolean sold;
    private String email;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Player(Long id, String name, int age, String category, double biddingPrice) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.category = category;
        this.biddingPrice = biddingPrice;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getBiddingPrice() {
		return biddingPrice;
	}

	public void setBiddingPrice(double biddingPrice) {
		this.biddingPrice = biddingPrice;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Player() {
		super();
	}
    
    

    // Constructors, getters, setters
}
