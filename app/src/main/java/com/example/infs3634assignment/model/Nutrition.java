package com.example.infs3634assignment.model;

//CLASS FOR NUTRITION OBJECT

public class Nutrition {
    private String title;
    private String amount;
    private String unit;

    public Nutrition(String title, String amount, String unit) {
        this.title = title;
        this.amount = amount;
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
