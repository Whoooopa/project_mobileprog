package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PostPage extends AppCompatActivity {
    List<PostImageList> postImageList;
    PostImageAdapter postImageAdapter;
    RecyclerView postImageRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_page);

        postImageList = new ArrayList<>();

        addPostImage();

        postImageAdapter = new PostImageAdapter(postImageList);

        postImageRV = findViewById(R.id.PostImageRV);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        postImageRV.setLayoutManager(gridLayoutManager);
        postImageRV.setAdapter(postImageAdapter);

        TextView backToHomeTV = findViewById(R.id.PostBackTV);
        TextView postPostTV = findViewById(R.id.PostPostTV);

        backToHomeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToHome = new Intent(PostPage.this, HomePage.class);
                startActivity(backToHome);
            }
        });

        postPostTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Post Image
            }
        });
    }

    public void addPostImage() {
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_cancel));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_cancel));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_cancel));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_cancel));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_cancel));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_launcher_background));
        postImageList.add(new PostImageList(R.drawable.ic_cancel));
    }
}