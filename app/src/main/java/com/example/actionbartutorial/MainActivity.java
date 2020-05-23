package com.example.actionbartutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.actionbartutorial.fragment.Account;
import com.example.actionbartutorial.fragment.FragmentErp;
import com.example.actionbartutorial.fragment.HomeFragment;
import com.example.actionbartutorial.fragment.MoreFragment;
import com.example.actionbartutorial.fragment.OfferFragment;
import com.example.actionbartutorial.fragment.SearchFragment;
import com.example.actionbartutorial.model.AllVersion;
import com.example.actionbartutorial.model.CurDate;
import com.example.actionbartutorial.model.Notification;
import com.example.actionbartutorial.model.Offer;
import com.example.actionbartutorial.model.UpdateApps;
import com.example.actionbartutorial.model.Version;
import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {


    public static String cid=null;
    public static String cid1=null;
    public static String cid2="0";
    public static String tablename=null;
    public static  String cname=null;
    public static  String cemail=null;
    public static  String cphone=null;
    public static  String cdate=null;
    public static String url=null;
    public static String featureid=null;
    public static  String caddress=null;
    public static  String uname=null;
    public static  String upassword=null;
    public static  String mid=null;


    View notificationBadge1,notificationBadge2;
    public static String gdate=null;
    BottomNavigationView bottomNavigationView;
    ActionBar actionbar;
    TextView textview;
    FragmentErp fragmentProduct1;
    HomeFragment homeFragment;
    OfferFragment offerFragment;
    SearchFragment searchFragment;
    Account account;
    MoreFragment moreFragment;
    TextView mybadge;
    RequestQueue requestQueue;


    ArrayList<Notification> notifications=null;
    ArrayList<Version> vList;
    static  ArrayList<Offer> alloffers=new ArrayList<>();

     String curl="https://cdash.boraksoftware.com/getoffer.php";
     public static  Version version=null;
     public static String serverdate=null,databasedate=null;
     RelativeLayout.LayoutParams layoutparams;
     TextView updatemessage;
     SharedPreferences sharedPreferences;
     SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         sharedPreferences=getSharedPreferences("MyPreference",MODE_PRIVATE);
         editor=sharedPreferences.edit();

         updatemessage=findViewById(R.id.updatemessage);
         ClearApplicationData.deleteCache(MainActivity.this);
         requestQueue= Volley.newRequestQueue(MainActivity.this);
         jSonParsing();
         getAppVersions();

         homeFragment= new HomeFragment();
         offerFragment=new OfferFragment();
         searchFragment=new SearchFragment();
         account=new Account();
         moreFragment=new MoreFragment();
         fragmentProduct1=new FragmentErp();

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
          getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        }


         bottomNavigationView=findViewById(R.id.bottom_navigation);
         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                homeFragment).commit();
                                break;
                    case R.id.nav_offer:
                        //refreshBadgeView();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                offerFragment).commit();
                                break;
                    case R.id.nav_search:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                searchFragment).commit();
                                break;
                    case R.id.nav_account:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                account).commit();
                                break;
                    case R.id.nav_more:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                moreFragment).commit();
                                break;
                }
                return true;
            }
        });
    }


    private void addBadgeView() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(1);
        notificationBadge1 = LayoutInflater.from(this).inflate(R.layout.mybadgelayout, menuView, false);
        mybadge =notificationBadge1.findViewById(R.id.badge_counter);
        String offers=String.valueOf(alloffers.size());
        // TextView _notificationBadgeTextView = notificationBadge1.findViewById();
        itemView.addView(notificationBadge1);
    }

    private void refreshBadgeView() {
        // boolean badgeIsVisible = notificationBadge.getVisibility() != VISIBLE;
        notificationBadge1.setVisibility(View.INVISIBLE);
        // button.setText(badgeIsVisible ? "Hide badge" : " Show badge");
    }


    public void getAppVersions(){
        ApiInterface apiInterface= ApiClient.getApiClient(MainActivity.this).create(ApiInterface.class);
        Call<AllVersion> allversion= apiInterface.getAllVersions();
        allversion.enqueue(new Callback<AllVersion>() {
            @Override
            public void onResponse(Call<AllVersion> call, Response<AllVersion> response) {
                  vList=response.body().getVersions();
                  if(vList.size()>0){
                     version=vList.get(0);
                    // Toast.makeText(MainActivity.this,"vdi:"+version.getvID1()+"vd2"+version.getvID2(),Toast.LENGTH_LONG).show();
                     // Log.d("vdi",version.getvID1());
                     // Log.d("vdi",version.getvID2());



                          if(!version.getvID1().equals(version.getvID2())){
                              updatemessage.setVisibility(View.VISIBLE);
                              updatemessage.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(!version.getUpdateurl().isEmpty()){
                                          Bundle bundle = new Bundle();
                                          bundle.putString("url",version.getUpdateurl());
                                          UpdateApps updateApps = new UpdateApps();
                                          updateApps.setArguments(bundle);
                                          getSupportFragmentManager().beginTransaction().replace(R.id.container, updateApps).commit();

                                          //getSupportFragmentManager().beginTransaction().replace(R.id.container,new UpdateApps()).commit();
                                      }

                                     // Toast.makeText(MainActivity.this,"url is empty",Toast.LENGTH_LONG).show();
                                  }



                              });
                          }



                    // String serverdate=getServerDate(version.getvDate());
                      Date  today=new Date();
                      SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
                  }
            }
            @Override
            public void onFailure(Call<AllVersion> call, Throwable t) {

            }
        });

    }



    public String getServerDate(final String date){
        ApiInterface apiInterface= ApiClient.getApiClient(MainActivity.this).create(ApiInterface.class);
        Call<CurDate> currentdate= apiInterface.getCurrentDate();
        currentdate.enqueue(new Callback<CurDate>() {
            @Override
            public void onResponse(Call<CurDate> call, Response<CurDate> response) {
               gdate=response.body().getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date databasedate = sdf.parse(date);
                    Date serverdate = sdf.parse(gdate);
                    if(databasedate.compareTo(serverdate)<0){
                       // Toast.makeText(MainActivity.this,"Update Available",Toast.LENGTH_LONG).show();

                        updatemessage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,new UpdateApps()).commit();
                            }
                        });
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CurDate> call, Throwable t) {

            }
        });

       return  gdate;
    }



    private void jSonParsing() {
        // Toast.makeText(Registration.this,"json parsing calling",Toast.LENGTH_LONG).show();
        alloffers.clear();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, curl, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("results");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject hit=jsonArray.getJSONObject(i);
                        String oid= hit.getString("oid");
                        String ohead= hit.getString("ohead");
                        String body= hit.getString("obody");
                        String startdate= hit.getString("ostdate");
                        String enddate= hit.getString("oenddate");
                        String offerstutus= hit.getString("ostutus");
                        alloffers.add(new Offer(oid,ohead,body,startdate,enddate,offerstutus));
                    }
                    if(alloffers.size()>0){
                          addBadgeView();
                          editor.putString("badgetext",String.valueOf(alloffers.size())).commit();
                          mybadge.setText(String.valueOf(sharedPreferences.getString("badgetext","5")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }






}
