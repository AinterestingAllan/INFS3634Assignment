package com.example.infs3634assignment.model;

// INGREDIENTS DATABASE FOR RECIPE

public class Ingredients {
    private String id;
    private String name;

    public Ingredients(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
