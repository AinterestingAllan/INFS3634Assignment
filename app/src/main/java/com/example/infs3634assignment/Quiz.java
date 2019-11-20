package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle b = getIntent().getExtras();
        String quizTitle = b.getString("quizTitle");
        String quizGluten = b.getString("quizGluten");
        String quizDairy = b.getString("quizDairy");
        String quizCalories = b.getString("quizCalories");
        String quizProtein = b.getString("quizProtein");
        String quizFat = b.getString("quizFat");
        String quizCarbs = b.getString("quizCarbs");


        LinearLayout linearLayout1 = findViewById(R.id.QuizLayout);

        // Question 1
        LinearLayout Q1 = linearLayout1.findViewById(R.id.Q1Layout);
        TextView Q1Question = Q1.findViewById(R.id.Q1Question);
        Q1Question.setText("How many carbohydrates are in " + quizTitle);

        RadioGroup Q1Radio = Q1.findViewById(R.id.Q1Radio);
        final RadioButton Q1B1 = Q1Radio.findViewById(R.id.radioButton1);
        final RadioButton Q1B2 = Q1Radio.findViewById(R.id.radioButton2);
        Q1B1.setText(quizCarbs.substring(quizCarbs.length() - 9)); //correct
        Q1B2.setText(quizProtein.substring(quizProtein.length() - 9));
        final TextView checkAnswer = Q1Radio.findViewById(R.id.checkAnswar);
        Q1B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q1B1.isChecked()){
                    checkAnswer.setText("Correct!");
                }else{
                    checkAnswer.setText("Wrong!");
                }
            }
        });

        Q1B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q1B2.isChecked()){
                    checkAnswer.setText("Wrong!");
                }else{
                    checkAnswer.setText("Wrong!");
                }
            }
        });

        // Question 2
        LinearLayout Q2 = linearLayout1.findViewById(R.id.Q2Layout);
        TextView Q2Question = Q2.findViewById(R.id.Q1Question);
        Q2Question.setText("How many proteins are in " + quizTitle);

        RadioGroup Q2Radio = Q2.findViewById(R.id.Q1Radio);
        final RadioButton Q2B1 = Q2Radio.findViewById(R.id.radioButton1);
        final RadioButton Q2B2 = Q2Radio.findViewById(R.id.radioButton2);
        Q2B1.setText(quizFat.substring(quizFat.length() - 9));
        Q2B2.setText(quizProtein.substring(quizProtein.length() - 9)); //correct
        final TextView checkAnswer2 = Q2Radio.findViewById(R.id.checkAnswar);
        Q2B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q2B2.isChecked()){
                    checkAnswer2.setText("Correct!");
                }else{
                    checkAnswer2.setText("Wrong!");
                }
            }
        });

        Q2B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q2B1.isChecked()){
                    checkAnswer2.setText("Wrong!");
                }else{
                    checkAnswer2.setText("Wrong!");
                }
            }
        });

        // Question 3
        LinearLayout Q3 = linearLayout1.findViewById(R.id.Q3Layout);
        TextView Q3Question = Q3.findViewById(R.id.Q1Question);
        Q3Question.setText("How many fats are in " + quizTitle);

        RadioGroup Q3Radio = Q3.findViewById(R.id.Q1Radio);
        final RadioButton Q3B1 = Q3Radio.findViewById(R.id.radioButton1);
        final RadioButton Q3B2 = Q3Radio.findViewById(R.id.radioButton2);
        Q3B1.setText(quizFat.substring(quizFat.length() - 9)); //correct
        Q3B2.setText(quizCarbs.substring(quizCarbs.length() - 9));
        final TextView checkAnswer3 = Q3Radio.findViewById(R.id.checkAnswar);
        Q3B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q3B1.isChecked()){
                    checkAnswer3.setText("Correct!");
                }else{
                    checkAnswer3.setText("Wrong!");
                }
            }
        });

        Q3B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q3B2.isChecked()){
                    checkAnswer3.setText("Wrong!");
                }else{
                    checkAnswer3.setText("Wrong!");
                }
            }
        });





        // Question 4
        LinearLayout Q4 = linearLayout1.findViewById(R.id.Q4Layout);
        TextView Q4Question = Q4.findViewById(R.id.Q1Question);
        Q4Question.setText("Is " + quizTitle + "gluten free?");

        RadioGroup Q4Radio = Q4.findViewById(R.id.Q1Radio);
        final RadioButton Q4B1 = Q4Radio.findViewById(R.id.radioButton1);
        final RadioButton Q4B2 = Q4Radio.findViewById(R.id.radioButton2);
        Q4B1.setText(quizGluten); //correct
        Boolean correctGluten = Boolean.parseBoolean(quizGluten);
        Boolean wrongGluten = !correctGluten;
        Q4B2.setText(wrongGluten.toString());
        final TextView checkAnswer4 = Q4Radio.findViewById(R.id.checkAnswar);
        Q4B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q4B1.isChecked()){
                    checkAnswer4.setText("Correct!");
                }else{
                    checkAnswer4.setText("Wrong!");
                }
            }
        });

        Q4B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q4B2.isChecked()){
                    checkAnswer4.setText("Wrong!");
                }else{
                    checkAnswer4.setText("Wrong!");
                }
            }
        });


        // Question 5
        LinearLayout Q5 = linearLayout1.findViewById(R.id.Q5Layout);
        TextView Q5Question = Q5.findViewById(R.id.Q1Question);
        Q5Question.setText("Is " + quizTitle + " dairy free?");

        RadioGroup Q5Radio = Q5.findViewById(R.id.Q1Radio);
        final RadioButton Q5B1 = Q5Radio.findViewById(R.id.radioButton1);
        final RadioButton Q5B2 = Q5Radio.findViewById(R.id.radioButton2);

        Q5B1.setText(quizDairy); //correct
        Boolean correctDairy = Boolean.parseBoolean(quizDairy);
        Boolean wrongDairy = !correctDairy;
        Q5B2.setText(wrongDairy.toString());
        final TextView checkAnswer5 = Q5Radio.findViewById(R.id.checkAnswar);
        Q5B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q5B1.isChecked()){
                    checkAnswer5.setText("Correct!");
                }else{
                    checkAnswer5.setText("Wrong!");
                }
            }
        });

        Q5B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Q5B2.isChecked()){
                    checkAnswer5.setText("Wrong!");
                }else{
                    checkAnswer5.setText("Wrong!");
                }
            }
        });



    }
}
