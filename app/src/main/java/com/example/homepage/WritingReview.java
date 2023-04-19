package com.example.homepage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class WritingReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_review);
        SharedPreferences pref = getSharedPreferences("MySharedPreferences", 0);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putInt("sequence3",0);
        prefEditor.commit();
        prefEditor.putInt("BRC",0);
        prefEditor.commit();
        prefEditor.putInt("BRW",0);
        prefEditor.commit();
        Flashcard fc1=new Flashcard("Name","how to call someone");
        Flashcard fc2=new Flashcard("age","how many years someone live");
        Flashcard fc3=new Flashcard("Gender","male or female");
        Flashcard fc4=new Flashcard("parent","father and mother");
        Flashcard fc5=new Flashcard("family","include all relatives");
        String[] front={fc1.Front,fc2.Front,fc3.Front,fc4.Front,fc5.Front};
        String[] back={fc1.Back,fc2.Back,fc3.Back,fc4.Back,fc5.Back};
        Flashcard[] set;
        set= new Flashcard[]{fc1, fc2, fc3, fc4, fc5};

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

TextView showans=new TextView(this);
        EditText et = new EditText(this);
        TextView tv = new TextView(this);
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(36);
        Button button = new Button(this);
        Button button2 = new Button(this);
Button button3=new Button(this);



        tv.setText(front[0]);
        button.setText("SUBMIT");
        button2.setText("SHOW ANSWER");
        button3.setText("next");
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                int i = pref.getInt("sequence3", 0);
                String ans;
                showans.setText("");
               ans=(et.getText().toString());
               et.setText("");


               if(ans.toUpperCase().equals(back[i].toUpperCase()))
               {
                   int c=pref.getInt("BRC",0);
                   c=c+1;
                   prefEditor.putInt("BRC",c);
                   prefEditor.commit();
               }
               else
               {
                   FileOutputStream fos;
                   try {
                       fos = openFileOutput("Review.txt",
                               MODE_APPEND);
                       String input=front[i]+" : "+back[i]+"\n";
                       fos.write(input.getBytes());
                       fos.close();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   int w = pref.getInt("BRW", 0);
                   w=w+1;
                   prefEditor.putInt("BRW",w);
                   prefEditor.commit();
               }
                i = i + 1;
               prefEditor.putInt("sequence3",i);
               prefEditor.commit();
                if (i == set.length) {
                    Intent t = new
                            Intent(WritingReview.this,  ReviewResult.class);
                    startActivity(t);
                    finish();
                }
                else
                    tv.setText(front[i]);
            }
        });
       button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int i = pref.getInt("sequence3", 0);
                showans.setText(back[i]);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showans.setText("");
                int w = pref.getInt("BRW", 0);
                w=w+1;
                prefEditor.putInt("BRW",w);
                prefEditor.commit();
                int i = pref.getInt("sequence3", 0);
                FileOutputStream fos;
                try {
                    fos = openFileOutput("Review.txt",
                            MODE_APPEND);
                    String input=front[i]+" : "+back[i]+"\n";
                    fos.write(input.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i = i + 1;
                prefEditor.putInt("sequence3",i);
                prefEditor.commit();
                if (i == set.length) {
                    Intent t = new
                            Intent(WritingReview.this,  ReviewResult.class);
                    startActivity(t);
                    finish();
                }
                else
                    tv.setText(front[i]);

            }
        });






        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(tv);
        ll.addView(showans);
        ll.addView(et);
        ll.addView(button);
        ll.addView(button2);
        ll.addView(button3);


        setContentView(ll);
    }
}