package com.example.infs3634assignment;

import android.provider.BaseColumns;

//SUPPLEMENTARY CLASS FOR QUIZ
public final class QuizContract {

    public QuizContract() {
    }

    public static class QustionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPRION1 = "option1";
        public static final String COLUMN_OPRION2 = "option2";
        public static final String COLUMN_ANSWER_NR = "answer_nr";

    }
}

