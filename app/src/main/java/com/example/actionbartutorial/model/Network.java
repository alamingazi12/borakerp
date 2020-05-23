package com.example.actionbartutorial.model;

import android.content.Context;

import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;
import com.example.actionbartutorial.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Network {



   static ArrayList<Version> vList;


    public static void getAppVersionsDate(Context context){
        ApiInterface apiInterface= ApiClient.getApiClient(context).create(ApiInterface.class);
        Call<AllVersion> allversion= apiInterface.getAllVersions();
        allversion.enqueue(new Callback<AllVersion>() {
            @Override
            public void onResponse(Call<AllVersion> call, Response<AllVersion> response) {
                vList=response.body().getVersions();
                if(vList.size()>0){

                  Version  version=vList.get(0);
                  MainActivity.databasedate=version.getvDate();
                    Date today=new Date();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat();

                }
            }

            @Override
            public void onFailure(Call<AllVersion> call, Throwable t) {

            }
        });



    }




    public static void getServerDate(Context context){
        ApiInterface apiInterface= ApiClient.getApiClient(context).create(ApiInterface.class);
        Call<CurDate> currentdate= apiInterface.getCurrentDate();
        currentdate.enqueue(new Callback<CurDate>() {
            @Override
            public void onResponse(Call<CurDate> call, Response<CurDate> response) {
                MainActivity.serverdate=response.body().getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            }
            @Override
            public void onFailure(Call<CurDate> call, Throwable t) {
            }
        });

    }
}
