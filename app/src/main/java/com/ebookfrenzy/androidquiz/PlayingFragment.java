package com.ebookfrenzy.androidquiz;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;


public class PlayingFragment extends Fragment {

    private Random random;
    //indexes of string array
    private static final int ENGLISH = 0;
    private static final int GREEK = 1;
    private static final int LATIN = 2;

    private static final String PIPE = "\\|";

    private Question question;
    private String questionType= ""; //either latin or greek
    private boolean itemSelected = false;

    private TextView questionNumber, questionText;
    private RadioGroup radioAnswers;
    private Button submitButton, quitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing, container, false);
        random = new Random();
        questionNumber = view.findViewById(R.id.questionNumber);
        questionText = view.findViewById(R.id.questionText);
        radioAnswers = view.findViewById(R.id.radioAnswers);
        submitButton = view.findViewById(R.id.submitButton);
        quitButton = view.findViewById(R.id.quitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //capture the value from the radioGroup and check for correct answer, if correct
                //update correct answers, if incorrect, update incorrect answers

                //move onto next question
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to results screen
                Navigation.findNavController(view).navigate(R.id.action_playingFragment_to_resultFragment);
            }
        });

        fireQuestion();
        populateUserInterface();
        return view;
    }

    private void addRadioButton(RadioGroup group, String text){
        RadioButton button = new RadioButton(this.getContext());
        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setButtonDrawable(android.R.drawable.btn_radio);
        group.addView(button);
    }

    private void fireQuestion(){
        question = getQuestion();
    }

    private String[] getRandomEnglishGreekLatin(){
        String[] array = getResources().getStringArray(R.array.classic_words);
        int index = random.nextInt(array.length);
        return array[index].split(PIPE);

    }

    private Question getQuestion(){
        String[] strAnswers = getRandomEnglishGreekLatin();
        Question question = new Question(strAnswers[ENGLISH], strAnswers[GREEK], strAnswers[LATIN]);
        String gameType = QuizTracker.getInstance().getGameType(); //either greek, latin, or mixed

        //need to determine what kind of question (and wrong answers) to pick based on gameType (greek/latin/mixed)
        if(gameType == "greek"){
            questionType = "greek";
        }else if(gameType == "latin"){
            questionType = "latin";
        }else {
            //game type is mixed, so need to randomly choose either a greek or latin question,
            //represented as a randomly chosen boolean
            boolean decider = new Random().nextBoolean();
            //if the random boolean is true, choose a greek question, else a latin one
            if(decider){
                questionType = "greek";
            }else{
                questionType = "latin";
            }
        }

        //generates 5 wrong answers
        while(question.getWrongAnswers().size() < 5){
            String[] strEnglishGreekLatin = getRandomEnglishGreekLatin();

            //need to check that its not the answer or one we've already picked, if so pick another
            //if the question is greek we are only choosing greek wrong answers
            if(questionType == "greek"){
                while (strEnglishGreekLatin[GREEK].equals(strAnswers[GREEK]) ||
                        question.getWrongAnswers().contains(strEnglishGreekLatin[GREEK])){
                    strEnglishGreekLatin = getRandomEnglishGreekLatin();
                }
                question.addWrongAnswer(strEnglishGreekLatin[GREEK]);
            }else{
                //if the question is latin we are only choosing latin wrong answers
                while (strEnglishGreekLatin[LATIN].equals(strAnswers[LATIN]) ||
                        question.getWrongAnswers().contains(strEnglishGreekLatin[LATIN])){
                    strEnglishGreekLatin = getRandomEnglishGreekLatin();
                }
                question.addWrongAnswer(strEnglishGreekLatin[LATIN]);
            }

        }
        return question;
    }

    private void populateUserInterface(){
        submitButton.setEnabled(false);
        itemSelected = false;

        //populate QuestionNumber textview
        String questionNumberText = getResources().getString(R.string.questionNumberText);
        int number = QuizTracker.getInstance().getQuestionNum();
        questionNumber.setText(String.format(questionNumberText, number));

        //set question text, depending on question type
        if(questionType == "greek"){
            questionText.setText(question.getGreekQuestionText());
        }else{
            //if its not a greek question it must be latin
            questionText.setText(question.getLatinQuestionText());
        }

        int randomPosition = random.nextInt(5);
        int counter =0;
        radioAnswers.removeAllViews();
        //for each of the 5 wrong answers
        for(String wrongAnswer : question.getWrongAnswers()){
            if(counter == randomPosition){
                //insert the correct answer
                if(questionType == "greek"){
                    addRadioButton(radioAnswers, question.getGreek());
                }else{
                    addRadioButton(radioAnswers, question.getLatin());
                }
            }else{
                addRadioButton(radioAnswers, wrongAnswer);
            }
            counter++;
        }
    }
}