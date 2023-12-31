package com.example.mobprogproject;

public class HomeImageList {
    private int image;
    private String name;
    private String comment;

    public HomeImageList(int image, String name, String comment) {
        this.image = image;
        this.name = name;
        this.comment = comment;
    }

    public int getImage() {
        return image;
    }

    public int setImage(){
        return this.image = image;
    }

    public String getName() {
        return name;
    }

    public String setName(){
        return this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public String setComment(){
        return this.comment = comment;
    }
}
