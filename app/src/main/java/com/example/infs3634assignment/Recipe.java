package com.example.infs3634assignment;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    public int id;
    public String title;
    public String sourceUrl;
    public String image;
    public ArrayList nutrition;
    public String readyInMinutes;
    public String servings;
    public String sourceName;

    public String getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(String readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Recipe(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList getNutrition() {
        return nutrition;
    }

    public void setNutrition(ArrayList nutrition) {
        this.nutrition = nutrition;
    }
}
