package com.example.actionbartutorial.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.actionbartutorial.R;

public class UpdateApps extends Fragment {


    WebView upwebview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.updatefragment,container,false);
         String url=  getArguments().getString("url");
         Toast.makeText(getContext(),"url:"+url,Toast.LENGTH_LONG).show();
        upwebview=view.findViewById(R.id.updateview);
        WebSettings webSettings= upwebview.getSettings();
        upwebview.setWebViewClient(new WebViewClient());
        webSettings.setJavaScriptEnabled(true);
        upwebview.loadUrl(url);
        return view;
    }
}
