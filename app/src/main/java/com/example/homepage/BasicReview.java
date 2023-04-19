package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;

public class BasicReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_review);
        SharedPreferences pref = getSharedPreferences("MySharedPreferences", 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putInt("sequence",0);
        prefEditor.commit();
        prefEditor.putInt("BRC",0);
        prefEditor.commit();
        prefEditor.putInt("BRW",0);
        prefEditor.commit();
        Button textbt=findViewById(R.id.button8);
        Button hardbt=findViewById(R.id.button5);
        Button goodbt=findViewById(R.id.button6);
        Button easybt=findViewById(R.id.button7);

        FileOutputStream fos;
        try {
            String reset="";
            fos = openFileOutput("Review.txt",
                    MODE_PRIVATE);
            fos.write(reset.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }





        Flashcard fc1=new Flashcard("Name","how to call someone");
        Flashcard fc2=new Flashcard("age","how many years live");
        Flashcard fc3=new Flashcard("Gender","male or female");
        Flashcard fc4=new Flashcard("parent","father and mother");
        Flashcard fc5=new Flashcard("family","include all relatives");


        Flashcard[] set1;
        set1= new Flashcard[]{fc1, fc2, fc3, fc4, fc5};
        textbt.setText(set1[0].Front);
      Flip flip=new Flip();
      flip.flipnum=1;
        hardbt.setEnabled(false);
        goodbt.setEnabled(false);
        easybt.setEnabled(false);
      textbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int i= pref.getInt("sequence",0);

                if(flip.flipnum==1) {
                    textbt.setBackgroundColor(Color.CYAN);
                    textbt.setText(set1[i].Back);
                   flip.flipnum=2;
                    hardbt.setEnabled(true);
                    goodbt.setEnabled(true);
                    easybt.setEnabled(true);
                }
                else {
                    textbt.setBackgroundColor(Color.parseColor("#2196F3"));
                    textbt.setText(set1[i].Front);
                    flip.flipnum =1;
                    hardbt.setEnabled(false);
                    goodbt.setEnabled(false);
                    easybt.setEnabled(false);
                }
            }
        });






    hardbt.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {

            int w = pref.getInt("BRW", 0);
            w=w+1;
            prefEditor.putInt("BRW",w);
            prefEditor.commit();
            int i = pref.getInt("sequence", 0);
            FileOutputStream fos;
            try {
                fos = openFileOutput("Review.txt",
                        MODE_APPEND);
                String input=set1[i].Front+" : "+set1[i].Back+"\n";
                fos.write(input.getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            i = i + 1;

            if (i == set1.length) {
                Intent t = new
                        Intent(BasicReview.this,  ReviewResult.class);
                startActivity(t);
                finish();
            }
            else {
                prefEditor.putInt("sequence", i);
                prefEditor.commit();
                if (flip.flipnum == 1) {

                    textbt.setBackgroundColor(Color.CYAN);
                    textbt.setText(set1[i].Back);
                    flip.flipnum = 2;
                    hardbt.setEnabled(true);
                    goodbt.setEnabled(true);
                    easybt.setEnabled(true);
                } else {
                    textbt.setBackgroundColor(Color.parseColor("#2196F3"));
                    textbt.setText(set1[i].Front);
                    flip.flipnum = 1;
                    hardbt.setEnabled(false);
                    goodbt.setEnabled(false);
                    easybt.setEnabled(false);
                }
            }
        }
    });
    goodbt.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            int c=pref.getInt("BRC",0);
            c=c+1;
            prefEditor.putInt("BRC",c);
            prefEditor.commit();
            int i = pref.getInt("sequence", 0);
            i = i + 1;
            if (i == set1.length) {
                Intent t = new
                        Intent(BasicReview.this, ReviewResult.class);
                startActivity(t);
                finish();
            } else {
                prefEditor.putInt("sequence", i);
                prefEditor.commit();
                if (flip.flipnum == 1) {
                    textbt.setBackgroundColor(Color.CYAN);
                    textbt.setText(set1[i].Back);
                    flip.flipnum = 2;
                    hardbt.setEnabled(true);
                    goodbt.setEnabled(true);
                    easybt.setEnabled(true);
                } else {
                    textbt.setBackgroundColor(Color.parseColor("#2196F3"));
                    textbt.setText(set1[i].Front);
                    flip.flipnum = 1;
                    hardbt.setEnabled(false);
                    goodbt.setEnabled(false);
                    easybt.setEnabled(false);
                }
            }
        }
    });
    easybt.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            int c=pref.getInt("BRC",0);
            c=c+1;
            prefEditor.putInt("BRC",c);
            prefEditor.commit();
            int i = pref.getInt("sequence", 0);
            i = i + 1;
            if (i == set1.length) {
                Intent t = new
                        Intent(BasicReview.this, ReviewResult.class);
                startActivity(t);
                finish();
            } else {
                prefEditor.putInt("sequence", i);
                prefEditor.commit();
                if (flip.flipnum == 1) {
                    textbt.setBackgroundColor(Color.CYAN);
                    textbt.setText(set1[i].Back);
                    flip.flipnum = 2;
                    hardbt.setEnabled(true);
                    goodbt.setEnabled(true);
                    easybt.setEnabled(true);
                } else {
                    textbt.setBackgroundColor(Color.parseColor("#2196F3"));
                    textbt.setText(set1[i].Front);
                    flip.flipnum = 1;
                    hardbt.setEnabled(false);
                    goodbt.setEnabled(false);
                    easybt.setEnabled(false);
                }
            }
        }
    });
}

    }
