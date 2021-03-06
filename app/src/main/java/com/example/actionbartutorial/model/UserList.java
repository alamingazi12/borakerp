package com.example.actionbartutorial.model;

import com.example.actionbartutorial.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserList {

    @SerializedName("users")
    @Expose
    ArrayList<User> user=new ArrayList<>();

    public ArrayList<User> getUser() {
        return user;
    }
}
