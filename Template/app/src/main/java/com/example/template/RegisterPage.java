package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button registerRegisterButton = findViewById(R.id.RegisterRegisterButton);
        TextView registerToLoginTV = findViewById(R.id.RegisterToLoginTV);

        registerToLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerToLogin = new Intent(RegisterPage.this, MainActivity.class);
                startActivity(registerToLogin);
            }
        });
    }
}