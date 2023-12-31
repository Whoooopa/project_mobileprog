package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class FollowingFollowerActivity extends AppCompatActivity implements View.OnClickListener {
    Button followingBtn, followerBtn;
    ImageButton cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_follower);

        Intent moveFollowingFollower = getIntent();
        String dataToDisplay = moveFollowingFollower.getStringExtra("fragment");

        followerBtn = findViewById(R.id.followingFollowerFollowersButton);
        followingBtn = findViewById(R.id.followingFollowerFollowingsButton);
        cancelBtn = findViewById(R.id.followingFollowerCancelButton);

        followerBtn.setOnClickListener(this);
        followingBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        if(dataToDisplay.equals("followings")) {
            //load followings
        }else if(dataToDisplay.equals("followers")){
            //load followers
        }
    }

    public void onClick(View v){
        if(v == followingBtn){
            //load followings
        }else if(v == followerBtn){
            //load followers
        }else if(v == cancelBtn){
            finish();
        }
    }
}