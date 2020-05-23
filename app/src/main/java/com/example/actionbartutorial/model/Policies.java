package com.example.actionbartutorial.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Policies {
    ArrayList<Policy> policies=new ArrayList();
    public ArrayList<Policy> getPolicies() {
        return policies;
    }
}
