package com.example.actionbartutorial.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.actionbartutorial.R;

public class WebsiteLink extends Fragment {

    WebView weblink;

   ProgressBar webprogressbar;
    public WebsiteLink(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.website,container,false);
        webprogressbar=view.findViewById(R.id.webprogress);
        // Toast.makeText(getActivity(),"value:"+name,Toast.LENGTH_LONG).show();

        weblink= view.findViewById(R.id.myweblink);
        WebSettings webSettings= weblink.getSettings();
        webSettings.setJavaScriptEnabled(true);



        // webView.loadUrl("https://www.journaldev.com");

        // https://boraksoftware.com/%e0%a6%95%e0%a6%be%e0%a6%b8%e0%a7%8d%e0%a6%9f%e0%a6%ae%e0%a6%be%e0%a6%b0-%e0%a6%b2%e0%a6%bf%e0%a6%b8%e0%a7%8d%e0%a6%9f/
        //webViewfacebook.loadUrl("https://www.facebook.com/sharmaraad");

        weblink.loadUrl("https://boraksoftware.com");
        weblink.setWebViewClient(new MyViewClient());


        return view;
    }

    private class MyViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);



            webprogressbar.setVisibility(View.GONE);
        }
    }

}
