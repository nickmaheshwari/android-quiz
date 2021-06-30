package com.ebookfrenzy.androidquiz;

//SINGLETON class
public class QuizTracker {

    //1. create encapsulated members
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private int questionNum = 0;
    private String name;
    private String gameType; //greek, latin, or mixed

    //2. provide a static instance of self
    private static QuizTracker quizTracker;

    //3. override the no-arg constructor as private
    private QuizTracker(){};

    //4. provide a static getInstance() method
    public static QuizTracker getInstance(){
        if(null == quizTracker){
            quizTracker = new QuizTracker();
            return quizTracker;
        }else{
            return quizTracker;
        }
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public String getName() {
        return name;
    }

    public String getGameType() {
        return gameType;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void reset(){
        setName("");
        setCorrectAnswers(0);
        setIncorrectAnswers(0);
        setQuestionNum(1);
        setGameType("");
    }



    public void start(String name, String gameType){
        setName(name);
        setGameType(gameType);
        setCorrectAnswers(0);
        setIncorrectAnswers(0);
        setQuestionNum(1);
    }

    public void again(){
        setQuestionNum(1);
        setCorrectAnswers(0);
        setIncorrectAnswers(0);
    }
}
