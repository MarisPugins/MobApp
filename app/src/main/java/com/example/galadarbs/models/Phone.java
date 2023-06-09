package com.example.galadarbs.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable {

    String home, mobile, office;

    public Phone(String home,
          String mobile,
          String office) {
        this.home = home;
        this.mobile = mobile;
        this.office = office;
    }

    protected Phone(Parcel in) {
        home = in.readString();
        mobile = in.readString();
        office = in.readString();
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getOffice() {
        return office;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(home);
        parcel.writeString(mobile);
        parcel.writeString(office);
    }
}
