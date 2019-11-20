package com.example.infs3634assignment.Connectivity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3634assignment.model.History;
import java.util.List;

@Dao
public interface HistoryDao {
    @Query("Select * from History")
    List<History> getAllHistory();

    @Insert
    void insertAHistory(History history);
}
