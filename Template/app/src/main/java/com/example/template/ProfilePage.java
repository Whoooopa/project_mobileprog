package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {
    Button editProfileBtn;
    Button followingsBtn;
    Button followersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        editProfileBtn = findViewById(R.id.profileEditProfileButton);
        followingsBtn = findViewById(R.id.profileFollowingsCountButton);
        followersBtn = findViewById(R.id.profileFollowersCountButton);

        ImageView profilePic = findViewById(R.id.profileImageView);
        TextView profileUsername = findViewById(R.id.profileUserNameTV);
        TextView profileBio = findViewById(R.id.profileBioTV);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveProfileEdit = new Intent(ProfilePage.this, ProfileEditPage.class);
                startActivity(moveProfileEdit);
            }
        });

        followingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveFollowingFollower = new Intent(ProfilePage.this, FollowingFollowerActivity.class);
                moveFollowingFollower.putExtra("fragment", "followings");
                startActivity(moveFollowingFollower);
            }
        });

        followersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveFollowingFollower = new Intent(ProfilePage.this, FollowingFollowerActivity.class);
                moveFollowingFollower.putExtra("fragment", "followers");
                startActivity(moveFollowingFollower);
            }
        });
    }
}