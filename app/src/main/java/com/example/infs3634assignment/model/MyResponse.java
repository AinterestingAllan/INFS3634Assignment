package com.example.infs3634assignment.model;

import java.util.List;

public class MyResponse {
    private List<Result> results;
    private int totalResults;

    public MyResponse(List<Result> results, int totalResults) {
        this.totalResults = totalResults;
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
