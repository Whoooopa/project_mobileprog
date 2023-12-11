package com.example.template;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

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
                Intent moveProfileEdit = new Intent(ProfileActivity.this, ProfileEditActivity.class);
                startActivity(moveProfileEdit);
            }
        });

        followingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveFollowingFollower = new Intent(ProfileActivity.this, FollowingFollowerActivity.class);
                moveFollowingFollower.putExtra("fragment", "followings");
                startActivity(moveFollowingFollower);
            }
        });

        followersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveFollowingFollower = new Intent(ProfileActivity.this, FollowingFollowerActivity.class);
                moveFollowingFollower.putExtra("fragment", "followers");
                startActivity(moveFollowingFollower);
            }
        });
    }
}