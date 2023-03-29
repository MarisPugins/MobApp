package com.example.galadarbs.models;

public class ArtistList {

    private String name;
    private int picture;


    public ArtistList(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

}
