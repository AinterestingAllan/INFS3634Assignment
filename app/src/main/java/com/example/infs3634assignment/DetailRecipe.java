package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        Bundle b = getIntent().getExtras();
        String receivingTitle = b.getString("recipeTitle");
        String receivingURL = b.getString("recipeURL");
        String receivingImage = b.getString("recipeImage");
        ArrayList receivingNutrition = b.getStringArrayList("recipeNutrition");

        String recipeTitle = receivingTitle;
        String recipeURL = receivingURL;
        String recipeImage = receivingImage;
        ArrayList recipeNutrition = receivingNutrition;

        TextView DetailTitle = findViewById(R.id.DetailTitle);
        DetailTitle.setText(recipeTitle);

        TextView DetailURL = findViewById(R.id.DetailURL);
        DetailURL.setText(recipeURL);

        ImageView DetailImage = findViewById(R.id.DetailImage);
        Glide.with(DetailImage.getContext()).load(recipeImage).into(DetailImage);



    }
}
