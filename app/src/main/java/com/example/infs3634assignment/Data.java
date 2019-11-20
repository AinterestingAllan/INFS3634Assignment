package com.example.infs3634assignment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634assignment.model.History;
import com.example.infs3634assignment.model.Result;
import java.util.List;

import java.util.HashMap;

public class Data {
    public static HashMap<String, Fragment> recipeNameAy = new HashMap<>();
    public static Fragment nowFragment;
    public static Result nowDetail = null;
    public static Result nowCooking = null;
    public static FragmentManager fragmentManager = null;
    public static List<History> historyList = null;
}
