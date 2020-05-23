package com.example.actionbartutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class FacebookActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        webView =findViewById(R.id.myview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

       // WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://www.facebook.com/sharmaraad");



    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack()){

            webView.goBack();
        }
        super.onBackPressed();
    }
}
