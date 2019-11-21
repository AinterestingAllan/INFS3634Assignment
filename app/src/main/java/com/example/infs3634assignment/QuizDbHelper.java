package com.example.infs3634assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.infs3634assignment.QuizContract;

import java.io.File;
import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

    public String title;
    public String gluten;
    public String dairy;
    public String calories;
    public String protein;
    public String fat;
    public String carbs;

    public QuizDbHelper(Context context, String title, String gluten, String dairy, String calories, String protein, String fat, String carbs) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.title = title;
        this.gluten = gluten;
        this.dairy = dairy;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "+
                QuizContract.QustionsTable.TABLE_NAME + " ( " +
                QuizContract.QustionsTable._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuizContract.QustionsTable.COLUMN_QUESTION + " TEXT, "+
                QuizContract.QustionsTable.COLUMN_OPRION1  + " TEXT, "+
                QuizContract.QustionsTable.COLUMN_OPRION2 + " TEXT, "+
                QuizContract.QustionsTable.COLUMN_ANSWER_NR  + " INTEGER" +
                ")";

        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QustionsTable.TABLE_NAME);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable(title, gluten, dairy, calories, protein, fat, carbs);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QustionsTable.TABLE_NAME);
        onCreate(db);
    }

    public void fillQuestionTable(String title, String gluten, String dairy, String calories, String protein, String fat, String carbs){

        Boolean correctDairy = Boolean.parseBoolean(dairy);
        Boolean wrongDairy = !correctDairy;

        Boolean correctGluten = Boolean.parseBoolean(gluten);
        Boolean wrongGluten = !correctGluten;

        Question q1 = new Question("How many carbs are in " + title ,carbs.substring(carbs.length() - 9),fat.substring(fat.length() - 9),1);
        addQuestion(q1);
        Question q2 = new Question("How many fats are in " + title,protein.substring(protein.length() - 9),fat.substring(fat.length() - 9),2);
        addQuestion(q2);
        Question q3 = new Question("How many proteins are in " + title , protein.substring(protein.length() - 9) ,carbs.substring(carbs.length() - 9),1);
        addQuestion(q3);
        Question q4 = new Question("Is " + title + " dairy free?",wrongDairy.toString() ,dairy,2);
        addQuestion(q4);
        Question q5 = new Question("Is " + title + " gluten free?",gluten,wrongGluten.toString(),1);
        addQuestion(q5);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QustionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuizContract.QustionsTable.COLUMN_OPRION1,question.getOptionn1());
        cv.put(QuizContract.QustionsTable.COLUMN_OPRION2,question.getOption2());
        cv.put(QuizContract.QustionsTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        db.insert(QuizContract.QustionsTable.TABLE_NAME,null,cv);

    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ QuizContract.QustionsTable.TABLE_NAME,null);
        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QustionsTable.COLUMN_QUESTION)));
                question.setOptionn1(c.getString(c.getColumnIndex(QuizContract.QustionsTable.COLUMN_OPRION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QustionsTable.COLUMN_OPRION2)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QustionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
