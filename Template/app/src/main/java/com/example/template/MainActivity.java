package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private void logToReg() {
        Intent loginToRegister = new Intent(MainActivity.this, RegisterPage.class);
        startActivity(loginToRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginLoginButton = findViewById(R.id.LoginLoginButton);
        TextView loginToRegisterTV = findViewById(R.id.LoginToRegisterTV);
        EditText loginEmailET = findViewById(R.id.LoginEmailET);
        EditText loginPasswordET = findViewById(R.id.LoginPasswordET);

        loginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginValidation = true;

                if (loginEmailET.getText().toString().trim().equals("")) {
                    loginEmailET.setError("Enter email");
                    loginValidation = false;
                }

                if (loginPasswordET.getText().toString().trim().equals("")) {
                    loginPasswordET.setError("Enter password");
                    loginValidation = false;
                }

                if (loginValidation) {
                    Intent loginToHome = new Intent(MainActivity.this, HomePage.class);
                    startActivity(loginToHome);
                }
            }
        });

        loginToRegisterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logToReg();
            }
        });
    }
}