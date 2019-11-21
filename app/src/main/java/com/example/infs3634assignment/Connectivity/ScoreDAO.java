package com.example.infs3634assignment.Connectivity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634assignment.model.Score;

import java.util.List;

@Dao
public interface ScoreDAO {

    @Insert
    public void insert(Score... scores);

    @Update
    public void update(Score... scores);

    @Delete
    public void delete(Score scores);

    @Query("SELECT * FROM Score")
    public List<Score> getScores();
}
