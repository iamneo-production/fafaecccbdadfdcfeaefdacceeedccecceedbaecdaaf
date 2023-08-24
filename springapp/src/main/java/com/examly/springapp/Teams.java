package com.examly.springapp;

public class Teams {
    private String name;
    private String country;

    public Teams(String name, String country) {
        this.name = name;
        this.country = country;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
