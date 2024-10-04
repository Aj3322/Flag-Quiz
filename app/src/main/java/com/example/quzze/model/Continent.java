package com.example.quzze.model;

import java.util.List;

public class Continent {
    private String name;
    private List<Country> countries;

    public Continent(String name, List<Country> countries) {
        this.name = name;
        this.countries = countries;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Country> getCountries() {
        return countries;
    }
}

