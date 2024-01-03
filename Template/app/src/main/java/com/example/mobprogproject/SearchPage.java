package com.example.mobprogproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        addSearches();

        searchImageAdapter = new SearchImageAdapter(searchImageList);

        searchPostRV = findViewById(R.id.SearchPostRV);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        searchPostRV.setLayoutManager(gridLayoutManager);
        searchPostRV.setAdapter(searchImageAdapter);

        //NavBar
        Button searchHomeButton = findViewById(R.id.SearchHomeButton);
        Button searchSearchButton = findViewById(R.id.SearchSearchButton);
        Button searchPostButton = findViewById(R.id.SearchPostButton);
        Button searchProfileButton = findViewById(R.id.SearchProfileButton);

        searchHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navbarToHome = new Intent(SearchPage.this, HomePage.class);
                startActivity(navbarToHome);
            }
        });

        searchSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        searchPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navbarToPost = new Intent(SearchPage.this, PostPage.class);
                startActivity(navbarToPost);
            }
        });

        searchProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navbarToProfile = new Intent(SearchPage.this, ProfilePage.class);
                startActivity(navbarToProfile);
            }
        });
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

    public void addSearches() {
        searchImageList.add(new SearchImageList("123", R.drawable.ic_launcher_background));
        searchImageList.add(new SearchImageList("1234", R.drawable.ic_launcher_foreground));
        searchImageList.add(new SearchImageList("134", R.drawable.ic_launcher_background));
        searchImageList.add(new SearchImageList("1342", R.drawable.ic_launcher_foreground));
        searchImageList.add(new SearchImageList("12345", R.drawable.ic_cancel));
    }
}