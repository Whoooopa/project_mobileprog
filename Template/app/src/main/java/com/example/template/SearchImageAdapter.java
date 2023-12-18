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

public class SearchImageAdapter extends RecyclerView.Adapter<SearchImageAdapter.MyViewHolder> {
    List<SearchImageList> searchImageList;
    Context myContext;

    public SearchImageAdapter(List<SearchImageList> searchImageList) {
        this.searchImageList = searchImageList;
    }

    public void setFilteredList(List<SearchImageList> filteredList) {
        this.searchImageList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View myView = inflater.inflate(R.layout.search_image_layout, parent, false);
        MyViewHolder myHolder = new MyViewHolder(myView);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchImageAdapter.MyViewHolder holder, int position) {
        int image = searchImageList.get(position).getImage();

        holder.imageIV.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return searchImageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIV;
        ConstraintLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageIV = itemView.findViewById(R.id.ItemImageIV);
            container = itemView.findViewById(R.id.SearchImageContainer);
        }
    }
}
