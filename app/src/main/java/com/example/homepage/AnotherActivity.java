package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
        import android.widget.Button;
import android.widget.Toast;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);




        Flashcard fc1=new Flashcard("Name","how to call someone");
        Flashcard fc2=new Flashcard("age","how many years live");
        Flashcard fc3=new Flashcard("Gender","male or female");
        Flashcard fc4=new Flashcard("parent","father and mother");
        Flashcard fc5=new Flashcard("family","include all relatives");


        Flashcard[] set1;
        set1= new Flashcard[]{fc1, fc2, fc3, fc4, fc5};


        Button bsbt=findViewById(R.id.button);
        Button mchoicerbt=findViewById(R.id.button3);
        Button wrbt=findViewById(R.id.button2);

        bsbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new
                        Intent(AnotherActivity.this,  BasicReview.class);
                startActivity(i);

            }
        });
        mchoicerbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new
                        Intent(AnotherActivity.this,  MultipleChoiceReview.class);
                startActivity(i);

            }
        });
        wrbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new
                        Intent(AnotherActivity.this,  WritingReview.class);
                startActivity(i);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottommenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, "Homepage selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Homepage.class));
                return true;
            case R.id.profile:
                Toast.makeText(this, "Profile page selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}