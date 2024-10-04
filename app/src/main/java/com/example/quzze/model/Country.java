package com.example.quzze.model;

public class Country {
    private String name;
    private int flagResource;

    public Country(String name, int flagResource) {
        this.name = name;
        this.flagResource = flagResource;
    }

    public String getName() {
        return name;
    }

    public int getFlagResource() {
        return flagResource;
    }
}
