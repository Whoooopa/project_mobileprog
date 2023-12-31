package com.example.mobprogproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.MyViewHolder> {
    List<PostImageList> postImageList;
    Context myContext;

    public PostImageAdapter(List<PostImageList> postImageList) {
        this.postImageList = postImageList;
    }

    @NonNull
    @Override
    public PostImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View myView = inflater.inflate(R.layout.post_image_layout, parent, false);
        MyViewHolder myHolder = new MyViewHolder(myView);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostImageAdapter.MyViewHolder holder, int position) {
        int image = postImageList.get(position).getImage();

        holder.imageIV.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return postImageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIV;
        ConstraintLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageIV = itemView.findViewById(R.id.PostImageIV);
            container = itemView.findViewById(R.id.PostImageContainer);
        }
    }
}
