package com.example.infs3634assignment.Connectivity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.infs3634assignment.model.History;
import com.example.infs3634assignment.model.Score;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM History")
    public List<History> getAllHistory();

    @Insert
    void insertAHistory(History history);

    @Update
    public void update(History... histories);

    @Delete
    public void delete(History histories);
}
