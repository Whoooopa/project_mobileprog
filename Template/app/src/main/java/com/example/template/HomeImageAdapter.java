package com.example.mobprogproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeImageAdapter extends RecyclerView.Adapter<HomeImageAdapter.MyViewHolder> {
    List<HomeImageList> homeImageList;
    Context myContext;

    public HomeImageAdapter(List<HomeImageList> homeImageList) {
        this.homeImageList = homeImageList;
    }

    @NonNull
    @Override
    public HomeImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View myView = inflater.inflate(R.layout.home_image_layout, parent, false);
        HomeImageAdapter.MyViewHolder myHolder = new HomeImageAdapter.MyViewHolder(myView);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeImageAdapter.MyViewHolder holder, int position) {
        int currentImage = homeImageList.get(position).getImage();
        String currentName = homeImageList.get(position).getName();
        String curentComment = homeImageList.get(position).getComment();

        holder.postImageIV.setImageResource(currentImage);
        holder.username.setText(currentName);
        holder.comment.setText(curentComment);
    }

    @Override
    public int getItemCount() {
        return homeImageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView postImageIV;
        ConstraintLayout container;
        TextView username, comment, homeToCommentTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            postImageIV = itemView.findViewById(R.id.ItemPostImageIV);
            container = itemView.findViewById(R.id.HomeImageContainer);
            username = itemView.findViewById(R.id.HomeCommentUsernameTV);
            comment = itemView.findViewById(R.id.HomeCommentTV);
        }
    }
}
