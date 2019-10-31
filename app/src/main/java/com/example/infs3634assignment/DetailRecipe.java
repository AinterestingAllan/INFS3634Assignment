package com.example.infs3634assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DetailRecipe extends AppCompatActivity implements QuizMenuFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener, RecipeMenuFragment.OnFragmentInteractionListener
 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

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

        TextView DetailTitle = findViewById(R.id.DetailTitle);
        DetailTitle.setText(recipeTitle);

        ImageView DetailImage = findViewById(R.id.DetailImage);
        Glide.with(DetailImage.getContext()).load(recipeImage).into(DetailImage);

        TextView DetailSource= findViewById(R.id.DetailSource);
        DetailSource.setText("By: " + recipeSource);

        TextView DetailMinutes = findViewById(R.id.DetailMinutes);
        DetailMinutes.setText("Ready in " + recipeMinutes + " minutes");

        TextView DetailServings = findViewById(R.id.DetailServings);
        DetailServings.setText("Serves: " + recipeServings);

        TextView DetailURL = findViewById(R.id.DetailURL);
        DetailURL.setText("View Recipe: \n" + recipeURL + "\n");

        TextView DetailNutrition = findViewById(R.id.DetailNutrition);
        DetailNutrition.setText(builder.toString());


        final BottomNavigationView BottomNav = findViewById(R.id.BottomNav);
        BottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.recipeicon:
                        switchToFragment1();
                        break;
                    case R.id.quizicon:
                        switchToFragment2();
                        break;
                    case R.id.profileicon:
                        switchToFragment3();
                        break;
                }
                return false;
            }
        });

    }

    public void switchToFragment1() {
        Intent i = new Intent(DetailRecipe.this, MainActivity.class);
        startActivity(i);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentSlot, new RecipeMenuFragment()).commit();
    }

    public void switchToFragment2() {
        Intent i = new Intent(DetailRecipe.this, MainActivity.class);
        startActivity(i);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentSlot, new QuizMenuFragment()).commit();
    }

    public void switchToFragment3() {
        Intent i = new Intent(DetailRecipe.this, MainActivity.class);
        startActivity(i);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragmentSlot, new ProfileFragment()).commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
