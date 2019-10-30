package com.example.infs3634assignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {

    private ArrayList<Recipe> mRecipe;
    private Context mContext;

    public mAdapter(Context context, ArrayList<Recipe> recipes) {
        mRecipe = recipes;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciperow, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Recipe recipe = mRecipe.get(position);
        String titletext = recipe.getTitle();
        holder.recipeTitle.setText(titletext);

        holder.recipeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailRecipe.class);
                Bundle b = new Bundle();
                final Recipe recipe = mRecipe.get(position);
                b.putString("recipeTitle",recipe.getTitle());
                b.putString("recipeURL", recipe.getSourceUrl());
                b.putString("recipeImage", recipe.getImage());
                b.putStringArrayList("recipeNutrition", recipe.getNutrition());
                i.putExtras(b);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            recipeTitle = (TextView) itemView.findViewById(R.id.RecipeTitle);


        }

    }
}