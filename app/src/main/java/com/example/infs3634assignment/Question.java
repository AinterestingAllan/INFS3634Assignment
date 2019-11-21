package com.example.infs3634assignment;

import android.os.Parcel;
import android.os.Parcelable;

//CLASS FOR QUESTIONS (QUIZZES)

public class Question implements Parcelable {
    private String question;
    private String optionn1;
    private String option2;
    private int answerNr;

    public Question() {
    }

    public Question(String question, String optionn1, String option2, int answerNr) {
        this.question = question;
        this.optionn1 = optionn1;
        this.option2 = option2;
        this.answerNr = answerNr;
    }

    protected Question(Parcel in) {
        question = in.readString();
        optionn1 = in.readString();
        option2 = in.readString();
        answerNr = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(optionn1);
        dest.writeString(option2);
        dest.writeInt(answerNr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionn1() {
        return optionn1;
    }

    public void setOptionn1(String optionn1) {
        this.optionn1 = optionn1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
}

