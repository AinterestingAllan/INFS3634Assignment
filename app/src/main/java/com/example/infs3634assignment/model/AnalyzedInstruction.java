package com.example.infs3634assignment.model;

import java.util.List;

public class AnalyzedInstruction {
    private String name;
    private List<Steps> steps;

    public AnalyzedInstruction(String name, List<Steps> steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }
}