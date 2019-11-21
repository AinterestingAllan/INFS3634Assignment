package com.example.infs3634assignment.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "History")
public class History {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String recipeTitle;
    private long duriation;
    private String comment;
    private int healthScore;

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public History(String recipeTitle, long duriation, String comment, int healthScore) {
        this.recipeTitle = recipeTitle;
        this.duriation = duriation;
        this.comment = comment;
        this.healthScore = healthScore;
    }

    public String getRecipe() {
        return recipeTitle;
    }

    public void setRecipe(String recipe) {
        this.recipeTitle = recipe;
    }

    public long getDuriation() {
        return duriation;
    }

    public void setDuriation(long duriation) {
        this.duriation = duriation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }
}
