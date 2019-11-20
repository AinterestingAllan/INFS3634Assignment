package com.example.infs3634assignment;

import com.example.infs3634assignment.model.History;

import java.util.List;

public interface AsyncTaskDelegate {
    void hanldeTaskResult(List<History> historyList);
}
