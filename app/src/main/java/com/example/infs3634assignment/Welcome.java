package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final LinearLayout item1 = findViewById(R.id.inforow);
        TextView infotitle = item1.findViewById(R.id.Title);
        TextView infobody = item1.findViewById(R.id.Body);
        infotitle.setText("");
        infobody.setText("Keto First is the gateway for first-time Keto dieters into the world of low-carb meals. Starting a new diet can be hard so we are here to help! \n");

        final LinearLayout item2 = findViewById(R.id.reciperow);
        TextView recipetitle = item2.findViewById(R.id.Title);
        TextView recipebody = item2.findViewById(R.id.Body);
        recipetitle.setText("Recipes");
        recipebody.setText("Educate yourself with Keto recipes filtered by different cuisines - each recipe shows nutritional details and links to the recipe website!\n");

        final LinearLayout item3 = findViewById(R.id.quizrow);
        TextView quiztitle = item3.findViewById(R.id.Title);
        TextView quizbody = item3.findViewById(R.id.Body);
        quiztitle.setText("Quizzes");
        quizbody.setText("Test your knowledge about Keto recipes and gain points for every correct question.\n");

        final LinearLayout item4 = findViewById(R.id.commentsrow);
        TextView commentstitle = item4.findViewById(R.id.Title);
        TextView commentsrow = item4.findViewById(R.id.Body);
        commentstitle.setText("Comments");
        commentsrow.setText("Add notes and comments to recipes to keep track of your thoughts and points of interest!\n");


        ImageButton nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Welcome.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


}
