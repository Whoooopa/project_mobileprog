package com.example.mobprogproject;

public class SearchImageList {

    private String name;
    private int image;

    public SearchImageList(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String setName(){
        return this.name = name;
    }

    public int getImage() {
        return image;
    }

    public int setImage(){
        return this.image = image;
    }
}
