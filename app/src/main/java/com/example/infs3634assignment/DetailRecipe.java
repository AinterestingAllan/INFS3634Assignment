package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.infs3634assignment.ProjectAdapter.StepAdapter;
import com.example.infs3634assignment.ProjectFragments.InProgress;
import com.example.infs3634assignment.ProjectFragments.ProfileFragment;
import com.example.infs3634assignment.ProjectFragments.QuizMenuFragment;
import com.example.infs3634assignment.ProjectFragments.RecipeMenuFragment;
import com.example.infs3634assignment.model.Steps;

import java.util.ArrayList;

public class DetailRecipe extends AppCompatActivity implements QuizMenuFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener, RecipeMenuFragment.OnFragmentInteractionListener {
    // go to InProgress fragment
    public Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);


        // Grab intent from Recipe List and set all views in Detail Recipe.
        Bundle b = getIntent().getExtras();
        final String receivingTitle = b.getString("recipeTitle");
        String receivingURL = b.getString("recipeURL");
        String receivingImage = b.getString("recipeImage");
        final ArrayList<String> recipeNutrition = b.getStringArrayList("recipeNutrition");
        String healthRank = b.getString("healthRank");
        String preM = b.getString("preM");
        String cookM = b.getString("cookM");
        final String gluten = b.getString("gluten");
        final String dairy = b.getString("dairy");

        TextView detail_title = findViewById(R.id.detail_title);

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

        detail_title.setText(receivingTitle);
        detail_url.setText(receivingURL);
        detail_hr.setText("The Health Rank: " + healthRank);
        detail_preM.setText("Preparation Time: " + preM + " minutes");
        detail_cookM.setText("Cooking Time: " + cookM + " minutes");
        detail_gluten.setText("Gluten free? " + gluten);
        detail_dairy.setText("Dairy free? " + dairy);
        nutrition1.setText(recipeNutrition.get(0));
        nutrition2.setText(recipeNutrition.get(1));
        nutrition3.setText(recipeNutrition.get(2));
        nutrition4.setText(recipeNutrition.get(3));

        RecyclerView detail_rv = findViewById(R.id.detail_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        detail_rv.setLayoutManager(linearLayoutManager);
        StepAdapter stepAdapter = new StepAdapter();

        //SET RECIPE STEPS OR SHOW NO STEPS
        try {
            stepAdapter.setData(Data.nowDetail.getAnalyzedInstructions().get(0).getSteps());
            detail_rv.setAdapter(stepAdapter);
        } catch (IndexOutOfBoundsException error) {
            Steps st1 = new Steps("No Recipe Instructions");
            ArrayList<Steps> errorList = new ArrayList<>();
            errorList.add(st1);
            stepAdapter.setData(errorList);
            detail_rv.setAdapter(stepAdapter);
        }

        Glide.with(detail_image.getContext()).load(receivingImage).into(detail_image);

        startButton = findViewById(R.id.detail_start);

        //START TIMER FOR COOKING
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.nowCooking = Data.nowDetail;
                FragmentManager fragmentManager = Data.fragmentManager;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(Data.recipeNameAy.get("inProgress"));
                InProgress inProgress = new InProgress();
                fragmentTransaction.add(R.id.fragmentSlot, inProgress);
                fragmentTransaction.hide(Data.nowFragment);
                fragmentTransaction.show(inProgress);
                Data.nowFragment = inProgress;
                Data.recipeNameAy.put("inProgress", inProgress);
                fragmentTransaction.commitAllowingStateLoss();
                finish();
                // Intent intent = new Intent(getApplicationContext(), stopwatch.class);
                // startActivity(intent);
            }
        });

        //PASS DATA FOR QUIZ
        Button quizbutton = findViewById(R.id.Quiz);
        quizbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailRecipe.this, Quiz.class);
                Bundle b = new Bundle();
                b.putString("quizTitle", receivingTitle);
                b.putString("quizGluten", gluten);
                b.putString("quizDairy", dairy);
                b.putString("quizCalories", recipeNutrition.get(0));
                b.putString("quizProtein", recipeNutrition.get(1));
                b.putString("quizFat", recipeNutrition.get(2));
                b.putString("quizCarbs", recipeNutrition.get(3));
                i.putExtras(b);
                startActivity(i);
            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
