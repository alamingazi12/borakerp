package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.actionbartutorial.MainActivity;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.SocialFragment;
import com.example.actionbartutorial.model.Version;

public class MoreFragment extends Fragment {

    TextView textView,textprivacy,versionstext;
    ImageView facebook,youtube,instagram,twitter;
    Button weblinkbtn,btn_logout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.more_fragment, container, false);
        weblinkbtn=view.findViewById(R.id.weblink_btn);
        btn_logout= view.findViewById(R.id.btn_logout);
        textprivacy=view.findViewById(R.id.privacy);
        textView=view.findViewById(R.id.colorfultext);
        textView.setText(Html.fromHtml("<Small><b>Borak<font color='#a53333'>ERP</font></b></Small>"));
        facebook=view.findViewById(R.id.facebookid);
        youtube=view.findViewById(R.id.youtubeid);
        instagram=view.findViewById(R.id.instagramid);
        twitter=view.findViewById(R.id.twitterid);
        versionstext=view.findViewById(R.id.apversion);
      // Toast.makeText(getActivity(),"version date:"+Network.getAppVersionsDate(getActivity()),Toast.LENGTH_LONG).show();

        final Version version= MainActivity.version;
        versionstext.setText(version.getvID1());
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("socialurl", version.getFburl());
                SocialFragment socialFragment = new SocialFragment();
                socialFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, socialFragment).commit();
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("socialurl", version.getYturl());
                SocialFragment socialFragment = new SocialFragment();
                socialFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, socialFragment).commit();

            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("socialurl", version.getTwurl());
                SocialFragment socialFragment = new SocialFragment();
                socialFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, socialFragment).commit();
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("socialurl", version.getInsurl());
                SocialFragment socialFragment = new SocialFragment();
                socialFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, socialFragment).commit();

            }
        });

        textprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new PrivacyFragment()).commit();
            }
        });

        weblinkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new WebsiteLink()).commit();
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getActivity(),"button clicked",Toast.LENGTH_LONG).show();
                if(MainActivity.uname!=null){
                    MainActivity.uname=null;
                    MainActivity.cid=null;
                    MainActivity.cdate=null;
                    MainActivity.caddress=null;
                    MainActivity.cphone=null;
                    MainActivity.cname=null;
                  //  Toast.makeText(getActivity(),"You logged out Successfully",Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                }else {
                    //Toast.makeText(getActivity(),"You already logged out",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

}
