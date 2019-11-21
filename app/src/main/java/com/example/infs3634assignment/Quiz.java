package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class Quiz extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS =30000;

    private static final String KEY_SCORE = "keyscore";
    private static final String KEY_QUESRION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";




    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textviewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private Button buttonConfiemNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currenrQuestion;


    private int score;
    private boolean answered;

    private long backPressedTime;

    Bundle b = getIntent().getExtras();
    String quizTitle = b.getString("quizTitle");
    String quizGluten = b.getString("quizGluten");
    String quizDairy = b.getString("quizDairy");
    String quizCalories = b.getString("quizCalories");
    String quizProtein = b.getString("quizProtein");
    String quizFat = b.getString("quizFat");
    String quizCarbs = b.getString("quizCarbs");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        updateQuestion();

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_vire_score);
        textviewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        buttonConfiemNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        if(savedInstanceState == null) {
            QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getAllQuestions();
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        }else{
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESRION_COUNT);
            currenrQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered){
                startCountDown();
            }else{
                updateCountDonwText();
                showSolution();
            }
        }

        buttonConfiemNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(Quiz.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();;
                }
            }
        });



       /* Bundle b = getIntent().getExtras();
        String quizTitle = b.getString("quizTitle");
        String quizGluten = b.getString("quizGluten");
        String quizDairy = b.getString("quizDairy");
        String quizCalories = b.getString("quizCalories");
        String quizProtein = b.getString("quizProtein");
        String quizFat = b.getString("quizFat");
        String quizCarbs = b.getString("quizCarbs");


        LinearLayout linearLayout1 = findViewById(R.id.QuizLayout);



        // com.example.infs3634assignment.Question 1
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

        // com.example.infs3634assignment.Question 2
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

        // com.example.infs3634assignment.Question 3
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





        // com.example.infs3634assignment.Question 4
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


        // com.example.infs3634assignment.Question 5
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


        TextView score = linearLayout1.findViewById(R.id.score);




       //String resultArray[] = new String[result.length];
        //for(int i=0;i<result.length;i++){
          //  resultArray[i] = String.valueOf(result[i]);
        //}
        String result1 = Integer.toString(result);
        score.setText(result1);*/
    }

    private void updateQuestion(){
        QuizDbHelper db = new QuizDbHelper(this);
        ArrayList<Question> questions = db.getAllQuestions();
        

    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currenrQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currenrQuestion.getQuestion());
            rb1.setText(currenrQuestion.getOptionn1());
            rb2.setText(currenrQuestion.getOption2());

            questionCounter++;
            textviewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfiemNext.setText("confirm");

            timeLeftMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            finishQuiz();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                updateCountDonwText();
            }

            @Override
            public void onFinish() {
                timeLeftMillis = 0;
                updateCountDonwText();
                checkAnswer();

            }
        }.start();
    }

    private void updateCountDonwText(){
        int minutes = (int)(timeLeftMillis/1000)/60;
        int seconds = (int)(timeLeftMillis/1000)%60;

        String timeFormetted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewCountDown.setText(timeFormetted);

        if(timeLeftMillis<10000){
            textViewCountDown.setTextColor(Color.RED);
        }else{
            textViewCountDown.setTextColor(textColorDefaultCd);
        }

    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) +1;


        if(answerNr == currenrQuestion.getAnswerNr()){
            score++;
            textViewScore.setText("Score: "+score);
        }

        showSolution();
    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);

        switch (currenrQuestion.getAnswerNr()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;

            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
        }

        if(questionCounter<questionCountTotal){
            buttonConfiemNext.setText("Next");
        }else {
            buttonConfiemNext.setText("Finish");
        }

    }

    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime +2000 >System.currentTimeMillis()){
            finishQuiz();
        }else{
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_QUESRION_COUNT,questionCounter);
        outState.putLong(KEY_MILLIS_LEFT,timeLeftMillis);
        outState.putBoolean(KEY_ANSWERED,answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST,questionList);
    }


}
