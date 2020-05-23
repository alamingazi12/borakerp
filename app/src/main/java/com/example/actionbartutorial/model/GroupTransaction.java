package com.example.actionbartutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class GroupTransaction implements Parcelable {

    public  String date;
    ArrayList<GroupItem> items=new ArrayList<>();


    protected GroupTransaction(Parcel in) {
        date = in.readString();
    }

    public static final Creator<GroupTransaction> CREATOR = new Creator<GroupTransaction>() {
        @Override
        public GroupTransaction createFromParcel(Parcel in) {
            return new GroupTransaction(in);
        }

        @Override
        public GroupTransaction[] newArray(int size) {
            return new GroupTransaction[size];
        }
    };

    public GroupTransaction() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public ArrayList<GroupItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GroupItem> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
    }
}
