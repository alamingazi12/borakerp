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

public class FacebookFragment extends Fragment {

    String url;

       WebView webViewfacebook;

    public FacebookFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.facebookfragment, container, false);
       // String myValue = this.getArguments().getString("message");
        //Bundle bundle=getArguments();
        view.findViewById(R.id.facebookbackbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
            }
        });
        //Toast.makeText(getActivity(),"value:"+bundle.getString(url,"alamin"),Toast.LENGTH_LONG).toString();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webViewfacebook =view.findViewById(R.id.facebookview);





        WebSettings webSettings = webViewfacebook.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webViewfacebook.setWebViewClient(new WebViewClient());

       // webView.loadUrl("https://www.journaldev.com");

       // https://boraksoftware.com/%e0%a6%95%e0%a6%be%e0%a6%b8%e0%a7%8d%e0%a6%9f%e0%a6%ae%e0%a6%be%e0%a6%b0-%e0%a6%b2%e0%a6%bf%e0%a6%b8%e0%a7%8d%e0%a6%9f/
        //webViewfacebook.loadUrl("https://www.facebook.com/sharmaraad");

        webViewfacebook.loadUrl("https://boraksoftware.com/%e0%a6%95%e0%a6%be%e0%a6%b8%e0%a7%8d%e0%a6%9f%e0%a6%ae%e0%a6%be%e0%a6%b0-%e0%a6%b2%e0%a6%bf%e0%a6%b8%e0%a7%8d%e0%a6%9f/");



    }



}
