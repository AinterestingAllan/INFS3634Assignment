package com.example.infs3634assignment.model;

import java.util.List;

//CLASS FOR RECIPE STEPS

public class Steps {
    private int number;
    private String step;
    private List<Ingredients> ingredients;
    private List<Equipment> equipment;
    //private List<Length> length;

    public Steps(String step) {
        this.step = step;

    }

    public Steps(int number, List<Ingredients> ingredients, String step,
                 List<Equipment> equipment) {
        this.number = number;
        this.ingredients = ingredients;
        this.equipment = equipment;
        this.step = step;
        //this.length = length;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    //    public List<Length> getLength() {
//        return length;
//    }
//
//    public void setLength(List<Length> length) {
//        this.length = length;
//    }
}
