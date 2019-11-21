package com.example.infs3634assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.infs3634assignment.QuizContract;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QustionsTable.TABLE_NAME);
        onCreate(db);
    }

    public void fillQuestionTable(){
        Question q1 = new Question("A is correct","A","B",1);
        addQuestion(q1);
        Question q2 = new Question("B is correct","A","B",2);
        addQuestion(q2);
        Question q3 = new Question("A is correct","A","B",1);
        addQuestion(q3);
        Question q4 = new Question("A is correct","A","B",1);
        addQuestion(q4);
        Question q5 = new Question("A is correct","A","B",1);
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
