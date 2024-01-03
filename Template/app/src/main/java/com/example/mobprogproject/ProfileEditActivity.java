package com.example.mobprogproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileEditActivity extends AppCompatActivity implements View.OnClickListener{

   Button saveBtn;
   ImageButton cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        cancelBtn = findViewById(R.id.profileEditCancelButton);
        saveBtn = findViewById(R.id.profileEditSaveButton);

        cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == cancelBtn) {
            finish();
        }else if(v == saveBtn){
            //save
            finish();
        }
    }
}