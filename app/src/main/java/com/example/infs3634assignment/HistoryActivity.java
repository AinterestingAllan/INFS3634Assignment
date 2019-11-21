package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.infs3634assignment.Connectivity.AppDatabase;
import com.example.infs3634assignment.Connectivity.HistorySelectAsyncTask;
import com.example.infs3634assignment.ProjectAdapter.HistoryAdapter;
import com.example.infs3634assignment.model.History;

import java.util.List;

//CLASS FOR HISTORY ACTIVITY

public class HistoryActivity extends AppCompatActivity implements AsyncTaskDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        AppDatabase db = AppDatabase.getInstance(this);
        HistorySelectAsyncTask historySelectAsyncTask = new HistorySelectAsyncTask();
        historySelectAsyncTask.setDatabase(db);
        historySelectAsyncTask.setDelegate(this);
        historySelectAsyncTask.execute();
    }

    @Override
    public void hanldeTaskResult(List<History> historyList) {
        RecyclerView rv = findViewById(R.id.history_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        HistoryAdapter historyAdapter = new HistoryAdapter();
        historyAdapter.setData(historyList);
        rv.setAdapter(historyAdapter);
    }
}
