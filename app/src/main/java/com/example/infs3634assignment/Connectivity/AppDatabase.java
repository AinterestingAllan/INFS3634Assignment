package com.example.infs3634assignment.Connectivity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.infs3634assignment.model.History;

// DATABASE HOLDING RECIPE HISTORY

// 告诉计算机这个类是操控数据库的类
@Database(entities = {History.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // 声明一个抽象DAO方法
    public abstract HistoryDao historyDao();

    public static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "nytDb")
                    .build();
        }
        return instance;
    }
}