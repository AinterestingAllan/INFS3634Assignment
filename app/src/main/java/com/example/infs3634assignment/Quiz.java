package com.example.infs3634assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

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

import com.example.infs3634assignment.Connectivity.AppDatabase;
import com.example.infs3634assignment.Connectivity.ScoreDAO;
import com.example.infs3634assignment.Connectivity.ScoreDatabase;
import com.example.infs3634assignment.model.Score;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

//CODE FOR QUIZ

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

    private static final String DB_PATH = "/data/data/com.example.infs3634assignment/databases/MyAwesomeQuiz.db";

    //CHECKS FOR EXISTING DB
    private void doDBCheck()
    {
        try{
            File file = new File(DB_PATH);
            file.delete();
        }catch(Exception ex)
        {}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        doDBCheck();

        //GRABS DATA FROM INTENT
        Bundle b = getIntent().getExtras();
        String quizTitle = b.getString("quizTitle");
        String quizGluten = b.getString("quizGluten");
        String quizDairy = b.getString("quizDairy");
        String quizCalories = b.getString("quizCalories");
        String quizProtein = b.getString("quizProtein");
        String quizFat = b.getString("quizFat");
        String quizCarbs = b.getString("quizCarbs");

        //SETS UP ALL VIEWS
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

        //CREATES QUIZ AND SETS QUESTIONS
        if(savedInstanceState == null) {
            QuizDbHelper dbHelper = new QuizDbHelper(this, quizTitle, quizGluten, quizDairy, quizCalories, quizProtein, quizFat, quizCarbs);

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

    }

    //SHOW NEXT QUESTION

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


    //START QUESTION COUNTDOWN
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

    //UPDATE COUNTDOWN AFTER ANSWER
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

    //CHECK IF ANSWER IS CORRECT
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

    //SHOW CORRECT SOLUTION
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

    //FINISH QUIZ AND ADD SCORE TO TOTAL QUIZ SCORE ON PROFILE
    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK,resultIntent);

        ScoreDatabase database = Room.databaseBuilder(getApplicationContext(), ScoreDatabase.class, "db-scores")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        ScoreDAO scoreDAO = database.getScoreDAO();

        int id = generateRandomInt(100000);

        Score s1 = new Score(id, score);
        scoreDAO.insert(s1);

        Toast.makeText(this, "Congratulations! You have completed a quiz. You received " + s1.getQuizScore() + " points", Toast.LENGTH_LONG).show();

        finish();
    }

    public static int generateRandomInt(int upperRange){
        Random random = new Random();
        return random.nextInt(upperRange);
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
