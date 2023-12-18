package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    List<SearchImageList> searchImageList;
    SearchImageAdapter searchImageAdapter;
    RecyclerView searchPostRV;
    SearchView searchSearchBarSV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        searchSearchBarSV = findViewById(R.id.SearchSearchBarSV);
        searchSearchBarSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        searchImageList = new ArrayList<>();

        addPost();

        searchImageAdapter = new SearchImageAdapter(searchImageList);

        searchPostRV = findViewById(R.id.SearchPostRV);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        searchPostRV.setLayoutManager(gridLayoutManager);
        searchPostRV.setAdapter(searchImageAdapter);
    }

    private void filterList(String text) {
        List<SearchImageList> filteredList = new ArrayList<>();

        for (SearchImageList image : searchImageList) {
            if (image.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(image);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No result", Toast.LENGTH_SHORT).show();
        }
        else {
            searchImageAdapter.setFilteredList(filteredList);
        }
    }

    public void addPost() {
        searchImageList.add(new SearchImageList("123", R.drawable.ic_launcher_background));
        searchImageList.add(new SearchImageList("1234", R.drawable.ic_launcher_foreground));
        searchImageList.add(new SearchImageList("134", R.drawable.ic_launcher_background));
        searchImageList.add(new SearchImageList("1342", R.drawable.ic_launcher_foreground));
        searchImageList.add(new SearchImageList("12345", R.drawable.ic_cancel));
    }
}