package com.example.rebinder.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by XJ on 2018/2/27 0027.
 */

public class Person implements Parcelable {
    private String name;
    private int gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public static Creator<Person> getCREATOR() {
        return CREATOR;
    }

    protected Person(Parcel in) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.gender);
    }
}
