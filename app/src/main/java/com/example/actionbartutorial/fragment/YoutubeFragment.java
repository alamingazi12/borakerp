package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.actionbartutorial.R;
import com.example.actionbartutorial.fragment.HomeFragment;

public class YoutubeFragment extends Fragment {


    WebView youtube;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.youtubefragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

          youtube=view.findViewById(R.id.yfragment);


         WebSettings webSettings = youtube.getSettings();
        webSettings.setJavaScriptEnabled(true);


        youtube.setWebViewClient(new WebViewClient());

        // webView.loadUrl("https://www.journaldev.com");

        // https://boraksoftware.com/%e0%a6%95%e0%a6%be%e0%a6%b8%e0%a7%8d%e0%a6%9f%e0%a6%ae%e0%a6%be%e0%a6%b0-%e0%a6%b2%e0%a6%bf%e0%a6%b8%e0%a7%8d%e0%a6%9f/
        //webViewfacebook.loadUrl("https://www.facebook.com/sharmaraad");

        youtube.loadUrl("https://boraksoftware.com/%e0%a6%95%e0%a6%be%e0%a6%b8%e0%a7%8d%e0%a6%9f%e0%a6%ae%e0%a6%be%e0%a6%b0-%e0%a6%b2%e0%a6%bf%e0%a6%b8%e0%a7%8d%e0%a6%9f/");
        view.findViewById(R.id.youtubebackbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
            }
        });
    }
}
