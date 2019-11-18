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

        String recipeTitle = receivingTitle;
        String recipeURL = receivingURL;
        String recipeImage = receivingImage;
        ArrayList recipeNutrition = receivingNutrition;
        String recipeMinutes = receivingMinutes;
        String recipeServings = receivingServings;
        String recipeSource = receivingSource;

        StringBuilder builder = new StringBuilder();
        String calories = recipeNutrition.get(0).toString();
        String amount1 = calories.substring(18, 23);
        String protein = recipeNutrition.get(1).toString();
        String amount2 = protein.substring(16, 21);
        String fat = recipeNutrition.get(2).toString();
        String amount3 = fat.substring(16, 21);
        String carbs = recipeNutrition.get(3).toString();
        String amount4 = carbs.substring(16, 21);

        builder.append(
                "Calories: " + amount1 + " cal" + "\n" +
                        "Protein: " + amount2 + " g" + "\n" +
                        "Fat: " + amount3 + " g" + "\n" +
                        "Carbohydrates: " + amount4 + " g");

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
        DetailNutrition.setText(builder.toString());

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), stopwatch.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
