package com.example.infs3634assignment.model;

public class Length {
    private String number;
    private String unit;

    public Length(String number, String unit) {
        this.number = number;
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
