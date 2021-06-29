package com.ebookfrenzy.androidquiz;

import java.util.HashSet;
import java.util.Set;

public class Question {

    private String english;
    private String latin;
    private String greek;

    private Set<String> wrongAnswers = new HashSet<String>();

    public Question(String english, String latin, String greek) {
        this.english = english;
        this.latin = latin;
        this.greek = greek;
    }

    public String getEnglish() {
        return english;
    }

    public String getLatin() {
        return latin;
    }

    public String getGreek() {
        return greek;
    }

    public Set<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void addWrongAnswer(String wrong){
        wrongAnswers.add(wrong);
    }

    public String getLatinQuestionText(){
        return "What is the Latin of " + english + "?";
    }

    public String getGreekQuestionText(){
        return "What is the Greek of " + english + "?";
    }
}
