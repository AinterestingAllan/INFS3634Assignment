package com.example.infs3634assignment.Connectivity;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.infs3634assignment.AsyncTaskDelegate;
import com.example.infs3634assignment.Data;
import com.example.infs3634assignment.model.History;
import com.example.infs3634assignment.HistoryActivity;

import java.util.List;

//ASYNC TASK FOR RECIPE HISTORY VIEW

public class HistorySelectAsyncTask extends AsyncTask<String, Integer, List<History>> {
    private AppDatabase db;
    private AsyncTaskDelegate delegate;


    public void setDatabase(AppDatabase db) {
        this.db = db;
    }

    public void setDelegate(AsyncTaskDelegate delegate) {
        this.delegate = delegate;
    }


    @Override
    protected List<History> doInBackground(String... strings) {
        List<History> historyList = db.historyDao().getAllHistory();
        Data.historyList = historyList;
        return historyList;
    }

    @Override
    protected void onPostExecute(List<History> historyList) {
        delegate.hanldeTaskResult(historyList);
    }
}