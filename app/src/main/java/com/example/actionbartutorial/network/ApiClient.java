package com.example.actionbartutorial.network;

import android.content.Context;

import androidx.constraintlayout.solver.Cache;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Cache cache;

    public static final String baseurl="https://cdash.boraksoftware.com/";
   public static Retrofit retrofit;
    public static Retrofit getApiClient(Context context){

        int cacheSize = 5 * 1024 * 1024; // 10 MB

        okhttp3.Cache cache=new okhttp3.Cache(context.getCacheDir(),cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        if(retrofit==null){

            retrofit=new Retrofit.Builder().baseUrl(baseurl).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        }
       return retrofit;

    }
}
