package com.example.homepage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the layout resource ID from the Intent extras
        int layoutResourceId = getIntent().getIntExtra("layoutResourceId", R.layout.quiz_layout);

        // Inflate the layout using the layout resource ID
        setContentView(layoutResourceId);
    }
}
