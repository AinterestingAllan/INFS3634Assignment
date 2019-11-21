package com.example.infs3634assignment.Connectivity;

import android.os.AsyncTask;

import com.example.infs3634assignment.model.History;

//ASYNC TASK FOR RECIPE HISTORY INSERT

public class HistoryInsertAsyncTask extends AsyncTask<History, Integer, String> {
    private AppDatabase db;

    public void setDatabase(AppDatabase db) {
        this.db = db;
    }

    @Override
    protected String doInBackground(History... histories) {
        for (int i = 0; i < histories.length; i++) {
            db.historyDao().insertAHistory(histories[i]);
        }
        return "插入成功！";
    }
}
