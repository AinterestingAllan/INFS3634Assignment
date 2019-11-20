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

import java.util.ArrayList;

public class DetailRecipe extends AppCompatActivity implements QuizMenuFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener, RecipeMenuFragment.OnFragmentInteractionListener
 {
     public Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);


        // Grab intent from Recipe List and set all views in Detail Recipe.
        Bundle b = getIntent().getExtras();
        String receivingTitle = b.getString("recipeTitle");
        String receivingURL = b.getString("recipeURL");
        String receivingImage = b.getString("recipeImage");
        ArrayList<String> recipeNutrition = b.getStringArrayList("recipeNutrition");
        String healthRank = b.getString("healthRank");
        String preM = b.getString("preM");
        String cookM = b.getString("cookM");
        String gluten = b.getString("gluten");
        String dairy = b.getString("dairy");

        ImageView detail_image = findViewById(R.id.detail_image);
        TextView detail_url = findViewById(R.id.detail_url);
        TextView detail_hr = findViewById(R.id.detail_hr);
        TextView detail_preM = findViewById(R.id.detail_preM);
        TextView detail_cookM = findViewById(R.id.detail_cookM);
        TextView detail_gluten = findViewById(R.id.detail_gluten);
        TextView detail_dairy = findViewById(R.id.detail_dairy);
        TextView nutrition1 = findViewById(R.id.nutrition1);
        TextView nutrition2 = findViewById(R.id.nutrition2);
        TextView nutrition3 = findViewById(R.id.nutrition3);
        TextView nutrition4 = findViewById(R.id.nutrition4);

        detail_url.setText("By: "+receivingURL);
        detail_hr.setText("The health rank: "+healthRank);
        detail_preM.setText("Prepare time: "+preM);
        detail_cookM.setText("Cooking time: "+cookM);
        detail_gluten.setText("Gluten free: "+gluten);
        detail_dairy.setText("Dairy free: "+dairy);
        nutrition1.setText(recipeNutrition.get(0));
        nutrition2.setText(recipeNutrition.get(1));
        nutrition3.setText(recipeNutrition.get(2));
        nutrition4.setText(recipeNutrition.get(3));

//        String recipeTitle = receivingTitle;
//        String recipeURL = receivingURL;
//        String recipeImage = receivingImage;
//        ArrayList recipeNutrition = receivingNutrition;
//        String recipeMinutes = receivingMinutes;
//        String recipeServings = receivingServings;
//        String recipeSource = receivingSource;
//
//        StringBuilder builder = new StringBuilder();
//        String calories = recipeNutrition.get(0).toString();
//        String amount1 = calories.substring(18, 23);
//        String protein = recipeNutrition.get(1).toString();
//        String amount2 = protein.substring(16, 21);
//        String fat = recipeNutrition.get(2).toString();
//        String amount3 = fat.substring(16, 21);
//        String carbs = recipeNutrition.get(3).toString();
//        String amount4 = carbs.substring(16, 21);

//        builder.append(
//                "Calories: " + amount1 + " cal" + "\n" +
//                        "Protein: " + amount2 + " g" + "\n" +
//                        "Fat: " + amount3 + " g" + "\n" +
//                        "Carbohydrates: " + amount4 + " g");
//
        ConstraintLayout detailname = findViewById(R.id.Detail);
        TextView titlename = detailname.findViewById(R.id.textView8);
        titlename.setText(receivingTitle);
//
//
//        ImageView DetailImage = findViewById(R.id.DetailImage);
        Glide.with(detail_image.getContext()).load(receivingImage).into(detail_image);
//
//        TextView DetailSource= findViewById(R.id.HealthRank);
//        DetailSource.setText("By: " + recipeSource);
//
//        TextView DetailMinutes = findViewById(R.id.PreparationM);
//        DetailMinutes.setText("Ready in " + recipeMinutes + " minutes");
//
//        TextView DetailServings = findViewById(R.id.glutenFree);
//        DetailServings.setText("Serves: " + recipeServings);
//
//        TextView DetailURL = findViewById(R.id.dairyFree);
//        DetailURL.setText("View Recipe: \n" + recipeURL + "\n");
//
//        TextView DetailNutrition = findViewById(R.id.DetailNutrition);
//        DetailNutrition.setText(builder.toString());

        startButton = findViewById(R.id.detail_start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), stopwatch.class);
                // startActivity(intent);
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
