package com.example.galadarbs.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Person implements Parcelable {
    String id, name, address, gender;
    ArrayList<String> songPlaylist;
    ArrayList<String> artistPlaylist;
    Phone phoneList;

    public Person(String id,
                  String name,
                  String address,
                  String gender,
                  ArrayList<String> songs,
                  ArrayList<String> artists,
                  Phone phones) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.songPlaylist = songs;
        this.artistPlaylist = artists;
        this.phoneList = phones;
    }

    protected Person(Parcel in) {
        id = in.readString();
        name = in.readString();
        address = in.readString();
        gender = in.readString();
        songPlaylist = in.createStringArrayList();
        artistPlaylist = in.createStringArrayList();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<String> getSongPlaylist() {
        return songPlaylist;
    }

    public ArrayList<String> getArtistPlaylist() {
        return artistPlaylist;
    }

    public Phone getPhoneList() {
        return phoneList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(gender);
        parcel.writeStringList(songPlaylist);
        parcel.writeStringList(artistPlaylist);
    }
}






