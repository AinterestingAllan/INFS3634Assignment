package com.example.infs3634assignment.Connectivity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.infs3634assignment.model.Score;

@Database(entities = {Score.class}, version = 1, exportSchema = false)
public abstract class ScoreDatabase extends RoomDatabase {
    public abstract ScoreDAO getScoreDAO();
}
