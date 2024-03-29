package com.example.infs3634assignment.ProjectAdapter;

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

import com.bumptech.glide.Glide;
import com.example.infs3634assignment.Data;
import com.example.infs3634assignment.DetailRecipe;
import com.example.infs3634assignment.R;
import com.example.infs3634assignment.model.Nutrition;
import com.example.infs3634assignment.model.Result;

import java.util.ArrayList;
import java.util.List;

//ADAPTER FOR RECIPE CLASS

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder> {

    private List<Result> mRecipe;
    private Context mContext;

    public MAdapter(Context context, List<Result> recipes) {
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
        // Setting recipe row details for Recycler View
        final Result recipe = mRecipe.get(position);
        String titletext = recipe.getTitle();
        holder.recipeTitle.setText(titletext);

        String imagetext = recipe.getImage();
        Glide.with(holder.recipeImage.getContext()).load(imagetext).into(holder.recipeImage);

        // Passing intents to Detail Recipe
        holder.recipeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailRecipe.class);
                Bundle b = new Bundle();
                final Result recipe = mRecipe.get(position);
                b.putString("recipeTitle", recipe.getTitle());
                b.putString("recipeURL", recipe.getSourceUrl());
                b.putString("recipeImage", recipe.getImage());
                b.putString("healthRank", recipe.getHealthScore() + "");
                b.putString("preM", recipe.getPreparationMinutes());
                b.putString("cookM", recipe.getCookingMinutes());
                b.putString("gluten", recipe.getGlutenFree() + "");
                b.putString("dairy", recipe.getDairyFree() + "");

                ArrayList<String> nutritionAy = new ArrayList<>();
                for (Nutrition n : recipe.getNutrition()) {
                    nutritionAy.add(n.getTitle() + " " + n.getAmount() + " " + n.getUnit());
                }
                b.putStringArrayList("recipeNutrition", nutritionAy);

                Data.nowDetail = recipe;
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
        public ImageView recipeImage;


        public ViewHolder(View itemView) {
            super(itemView);
            recipeTitle = (TextView) itemView.findViewById(R.id.RecipeTitle);
            recipeImage = itemView.findViewById(R.id.RecipeImage);


        }

    }
}