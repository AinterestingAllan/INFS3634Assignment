package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.infs3634assignment.model.Result;
import com.example.infs3634assignment.model.stopwatch;

import java.util.ArrayList;

public class DetailRecipe extends AppCompatActivity implements QuizMenuFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener, RecipeMenuFragment.OnFragmentInteractionListener
 {
     public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);


        // Grab intent from Recipe List and set all views in Detail Recipe.
        Bundle b = getIntent().getExtras();
        String receivingTitle = b.getString("recipeTitle");
        String receivingURL = b.getString("recipeURL");
        String receivingImage = b.getString("recipeImage");
        ArrayList receivingNutrition = b.getStringArrayList("recipeNutrition");
        String receivingMinutes = b.getString("recipeMinutes");
        String receivingServings = b.getString("recipeServings");
        String receivingSource = b.getString("recipeSource");

        final String recipeTitle = receivingTitle;
        String recipeURL = receivingURL;
        String recipeImage = receivingImage;
        ArrayList recipeNutrition = receivingNutrition;
        String recipeMinutes = receivingMinutes;
        String recipeServings = receivingServings;
        String recipeSource = receivingSource;

        ConstraintLayout detailname = findViewById(R.id.Detail);
        TextView titlename = detailname.findViewById(R.id.textView8);
        titlename.setText(recipeTitle);


        ImageView DetailImage = findViewById(R.id.DetailImage);
        Glide.with(DetailImage.getContext()).load(recipeImage).into(DetailImage);

        TextView DetailSource= findViewById(R.id.HealthRank);
        DetailSource.setText("By: " + recipeSource);

        TextView DetailMinutes = findViewById(R.id.PreparationM);
        DetailMinutes.setText("Ready in " + recipeMinutes + " minutes");

        TextView DetailServings = findViewById(R.id.glutenFree);
        DetailServings.setText("Serves: " + recipeServings);

        TextView DetailURL = findViewById(R.id.dairyFree);
        DetailURL.setText("View Recipe: \n" + recipeURL + "\n");

        TextView DetailNutrition = findViewById(R.id.DetailNutrition);
        DetailNutrition.setText("n/a");

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), stopwatch.class);
                startActivity(intent);
            }
        });

        Button quizbutton = findViewById(R.id.Quiz);
        quizbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailRecipe.this, Quiz.class);
                startActivity(i);
            }
        });



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
