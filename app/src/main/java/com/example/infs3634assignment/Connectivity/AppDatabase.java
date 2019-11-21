package com.example.infs3634assignment.Connectivity;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.infs3634assignment.model.History;

// 告诉计算机这个类是操控数据库的类
@Database(entities = {History.class}, version = 1,exportSchema = false)  // Replace "Book.class" with whatever your Book entity class is.
public abstract class AppDatabase extends RoomDatabase {

    // 声明一个抽象DAO方法
    public abstract HistoryDao historyDao();          // Replace BookDao with whatever you name your DAO

    public static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "nytDb")
                    .build();
        }
        return instance;
    }
}