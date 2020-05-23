package com.example.actionbartutorial.model;

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

public class SocialFragment extends Fragment {

    ProgressBar progressBar;

   WebView socilalwebview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.socialfragment, container, false);

        String socialurl=getArguments().getString("socialurl");
        socilalwebview=view.findViewById(R.id.socialview);
        progressBar=view.findViewById(R.id.socialprogress);
        WebSettings webSettings= socilalwebview.getSettings();
        socilalwebview.setWebViewClient(new MyViewClient());
        webSettings.setJavaScriptEnabled(true);
        socilalwebview.loadUrl(socialurl);

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


            progressBar.setVisibility(View.GONE);
        }
    }
}
