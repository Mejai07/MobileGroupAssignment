package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class ReviewResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences("MySharedPreferences", 0);
        setContentView(R.layout.activity_review_result);
       TextView ctv=findViewById(R.id.textView8);
       TextView wtv=findViewById(R.id.textView9);
       ctv.setTextColor(Color.GREEN);
       wtv.setTextColor(Color.RED);
       ctv.setText("Correct answers:"+pref.getInt("BRC",0));
       wtv.setText("Incorrect answers:"+pref.getInt("BRW",0));
       Button viewbt=findViewById(R.id.button4);


       viewbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new
                        Intent(ReviewResult.this,  ViewWrongs.class);
                startActivity(i);
                finish();
            }
        });
    }
}