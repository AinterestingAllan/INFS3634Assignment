package com.example.infs3634assignment.model;

import java.util.List;

public class Result {
    private String id;
    private String title;
    private String image;
    private Boolean glutenFree;
    private Boolean dairyFree;
    private Boolean cheap;
    private Boolean lowFodmap;
    private String preparationMinutes;
    private String cookingMinutes;
    private String sourceUrl;
    private int healthScore;
    private List<Nutrition> nutrition;

    public Result(String id, String title, String image,
                  Boolean glutenFree, Boolean dairyFree, Boolean cheap,
                  Boolean lowFodmap, String preparationMinutes, String cookingMinutes,
                  String sourceUrl, int healthScore, List<Nutrition> nutrition) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.cheap = cheap;
        this.lowFodmap = lowFodmap;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.sourceUrl = sourceUrl;
        this.healthScore = healthScore;
        this.nutrition = nutrition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public Boolean getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(Boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public Boolean getCheap() {
        return cheap;
    }

    public void setCheap(Boolean cheap) {
        this.cheap = cheap;
    }

    public Boolean getLowFodmap() {
        return lowFodmap;
    }

    public void setLowFodmap(Boolean lowFodmap) {
        this.lowFodmap = lowFodmap;
    }

    public String getPreparationMinutes() {
        return preparationMinutes;
    }

    public void setPreparationMinutes(String preparationMinutes) {
        this.preparationMinutes = preparationMinutes;
    }

    public String getCookingMinutes() {
        return cookingMinutes;
    }

    public void setCookingMinutes(String cookingMinutes) {
        this.cookingMinutes = cookingMinutes;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public List<Nutrition> getNutrition() {
        return nutrition;
    }

    public void setNutrition(List<Nutrition> nutrition) {
        this.nutrition = nutrition;
    }
}
