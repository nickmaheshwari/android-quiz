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

    private TextView questionNumber, questionText;
    private RadioGroup radioAnswers;
    private Button submitButton, quitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing, container, false);
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
        addRadioButton(radioAnswers, "hello");
        return view;
    }

    private void addRadioButton(RadioGroup group, String text){
        RadioButton button = new RadioButton(this.getContext());
        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setButtonDrawable(android.R.drawable.btn_radio);
        group.addView(button);
    }
}