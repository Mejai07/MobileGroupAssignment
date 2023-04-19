package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {


    private Button logOutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        logOutDetails();
    }

    private void logOutDetails(){

        logOutBtn = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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