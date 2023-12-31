package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    List<HomeImageList> homeImageList;
    HomeImageAdapter homeImageAdapter;
    RecyclerView homePostRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        homeImageList = new ArrayList<>();

        addPost();

        homeImageAdapter = new HomeImageAdapter(homeImageList);

        homePostRV = findViewById(R.id.HomePostRV);
        homePostRV.setLayoutManager(new LinearLayoutManager(this));
        homePostRV.setAdapter(homeImageAdapter);

        Button homeHomeButton = findViewById(R.id.HomeHomeButton);
        Button homeSearchButton = findViewById(R.id.HomeSearchButton);
        Button homePostButton = findViewById(R.id.HomePostButton);
        Button homeProfileButton = findViewById(R.id.HomeProfileButton);

        homeHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        homeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navbarToSearch = new Intent(HomePage.this, SearchPage.class);
                startActivity(navbarToSearch);
            }
        });

        homePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navbarToPost = new Intent(HomePage.this, PostPage.class);
                startActivity(navbarToPost);
            }
        });

        homeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navbarToProfile = new Intent(HomePage.this, ProfilePage.class);
                startActivity(navbarToProfile);
            }
        });
    }

    public void addPost() {
        homeImageList.add(new HomeImageList(R.drawable.ic_launcher_background, "Mar", "Hello World!"));
        homeImageList.add(new HomeImageList(R.drawable.ic_launcher_background, "Gab", "Hello 123!"));
        homeImageList.add(new HomeImageList(R.drawable.ic_launcher_background, "Dan", "Hello 456!"));
    }
}