package com.ebookfrenzy.androidquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class WelcomeFragment extends Fragment {

    private EditText editName;
    private Button greekButton;
    private Button latinButton;
    private Button mixedButton;
    private Button exitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        editName = view.findViewById(R.id.editName);
        greekButton = view.findViewById(R.id.greekButton);
        latinButton = view.findViewById(R.id.latinButton);
        mixedButton = view.findViewById(R.id.mixedButton);
        exitButton = view.findViewById(R.id.exitButton);
        //get value from editName
        String name = editName.getText().toString();

        greekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign name to Singleton, navigate to playing fragment
                QuizTracker.getInstance().setName(name);
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_playingFragment);
            }
        });

        latinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get value from editName, assign name to Singleton, navigate to playing fragment
                QuizTracker.getInstance().setName(name);
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_playingFragment);
            }
        });

        mixedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get value from editName, assign name to Singleton, navigate to playing fragment
                QuizTracker.getInstance().setName(name);
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_playingFragment);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


        return view;
    }


}