package com.example.infs3634assignment.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//CLASS ENTITY FOR SCORES

@Entity(tableName = "Score")
public class Score {

    public Score(int id, int quizScore) {
        this.id = id;
        this.quizScore = quizScore;
    }

    @NonNull
    public int quizScore;

    @PrimaryKey
    @NonNull
    public int id;

    public int getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
    }
}
