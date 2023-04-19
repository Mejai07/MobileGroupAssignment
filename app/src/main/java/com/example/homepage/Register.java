package com.example.homepage;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private EditText aEmail;
    private EditText aPass;
    private Button regBtn;
    private TextView aLogInHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register();
    }

    private void register(){

        aEmail=findViewById(R.id.email_reg);
        aPass=findViewById(R.id.password_reg);
        regBtn=findViewById(R.id.btn_reg);
        aLogInHere=findViewById(R.id.login_here);

        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String email = aEmail.getText().toString().trim();
                String pass = aPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    aEmail.setError("Email Required!");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    aPass.setError("Password Required!");


                }

                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);




            }
        });

        aLogInHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);

            }
        });
    }
}