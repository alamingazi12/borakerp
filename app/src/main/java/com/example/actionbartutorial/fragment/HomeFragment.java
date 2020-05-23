package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.actionbartutorial.model.Allicon;
import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;
import com.example.actionbartutorial.model.Employee;
import com.example.actionbartutorial.adapter.FirstAdapter;
import com.example.actionbartutorial.MainActivity;
import com.example.actionbartutorial.NotificationActivity;
import com.example.actionbartutorial.model.NotificationList;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.adapter.IconAdapter;
import com.example.actionbartutorial.model.AllVersion;
import com.example.actionbartutorial.model.CurDate;
import com.example.actionbartutorial.model.Icon;
import com.example.actionbartutorial.model.Notification;
import com.example.actionbartutorial.model.UpdateApps;
import com.example.actionbartutorial.model.User;
import com.example.actionbartutorial.model.UserList;
import com.example.actionbartutorial.model.Version;
import com.example.actionbartutorial.model.WebUrls;
import com.example.actionbartutorial.model.Website;
import com.github.ybq.android.spinkit.style.Circle;
import com.squareup.picasso.Picasso;

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

public class HomeFragment extends Fragment {
    FragmentInfo fragmentInfo=null;
    String noticount=null;
    ImageView messageicon;
    RecyclerView mrecyclerview;
    String url="http://cdash.boraksoftware.com/comicon";
    ProgressBar progressBar;
    TextView notification;
   public static ArrayList<Notification> notiList=new ArrayList<>();
   ArrayList<WebUrls> webUrls,webUrls2,webUrls3;

   ArrayList<Version> vList;



 public   ArrayList<Icon> images,images2,images3;



   User user=null;





   TextView username,useraddress;

    //public HomeFragment(){}
   // SwipeRefreshLayout homerefresh;
    ImageView imageView;
    EditText editText;
    ArrayList<Employee> employees;
    RecyclerView recyclerView1;
    private MyPagerAdapter mSectionsPagerAdapter;
    RequestQueue requestQueue;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.homefragment, container, false);
       // Version version=MainActivity.version;

        getAppVersions();

        username=view.findViewById(R.id.editinfo);
        recyclerView1=view.findViewById(R.id.recyclergroup1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        requestQueue= Volley.newRequestQueue(getActivity());
       // jSonParsing();

        getWebUrl();

        //homerefresh=view.findViewById(R.id.homerefreshlayout);
       /*homerefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(MainActivity.cid!=null){

                    getIconInfo(MainActivity.cid);
                }else {

                    getIconInfo("0");
                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homerefresh.setRefreshing(false);
                    }
                },400);
            };
        });

        */
         fragmentInfo=new FragmentInfo();
         mrecyclerview=view.findViewById(R.id.recyleimage);
         useraddress=view.findViewById(R.id.caddress);
         imageView=view.findViewById(R.id.profile);
        messageicon=view.findViewById(R.id.notificatioicon);
        recyclerView1=view.findViewById(R.id.recyclergroup1);
         progressBar=view.findViewById(R.id.iconspin_kit);


        Circle circle=new Circle();
        progressBar.setIndeterminateDrawable(circle);
       // progressBar.setVisibility(View.INVISIBLE);

        fillArrayList();

        if(MainActivity.cid==null){

          getDefaultData();
        }






        useraddress.setText(MainActivity.caddress);
        useraddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddata();
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                senddata();

            }
        });
/*


 */
        if(MainActivity.cid!=null){
            String urlimg=url+"/"+MainActivity.url;

           Picasso.get().load(urlimg).into(imageView);

           /*
            Glide.with(getActivity())
                    .load(urlimg)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.erp)
                    .into(imageView);

            */
        }
        username.setText(MainActivity.cname);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                senddata();

                // Toast.makeText(getActivity(),"text clicked",Toast.LENGTH_LONG).show();
            }
        });

        messageicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
        notification=view.findViewById(R.id.count);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });


        if(MainActivity.cid!=null){
            ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
            Call<NotificationList> notificationinfo= apiInterface.getNotifications(MainActivity.cid);
            notificationinfo.enqueue(new Callback<NotificationList>() {
                @Override
                public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                    notiList=response.body().getResults();
                    if(!notiList.isEmpty()){

                        noticount=Integer.toString(notiList.size());
                        notification.setText(noticount);
                        // Toast.makeText(getActivity(),"size"+noticount,Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<NotificationList> call, Throwable t) {

                    Toast.makeText(getActivity(),"No data found",Toast.LENGTH_LONG).show();
                }
            });

        }





        //  mSectionsPagerAdapter = new MyPagerAdapter(getSu);
      //  mSectionsPagerAdapter= new MyPagerAdapter(getActivity().getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        mrecyclerview.setLayoutManager(gridLayoutManager);

        if(MainActivity.cid!=null ){


              getIconInfo(MainActivity.cid);

        }
        else  {

            getIconInfo("0");

        }

        if(savedInstanceState!=null){


            if(webUrls2==null){
                webUrls2=savedInstanceState.getParcelableArrayList("weburls");
                webUrls3=webUrls2;
                FirstAdapter firstAdapter=new FirstAdapter(webUrls2,getActivity());
                recyclerView1.setAdapter(firstAdapter);
                firstAdapter.setOnItemClickListener(new FirstAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        //String productname=employees.get(position).getName()+"-"+employees.get(position).getProductname();

                        String weburl=webUrls.get(position).getUrl();
                        Bundle bundle = new Bundle();
                        bundle.putString("producturl", weburl);
                        FragmentErp fragmentProduct1 = new FragmentErp();
                        fragmentProduct1.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentProduct1).commit();
                    }});



            }
            if(webUrls3==null){

                webUrls2=savedInstanceState.getParcelableArrayList("weburls2");
                webUrls3=webUrls2;
                FirstAdapter firstAdapter=new FirstAdapter(webUrls2,getActivity());
                recyclerView1.setAdapter(firstAdapter);
                firstAdapter.setOnItemClickListener(new FirstAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        //String productname=employees.get(position).getName()+"-"+employees.get(position).getProductname();
                        String weburl=webUrls.get(position).getUrl();
                        Bundle bundle = new Bundle();
                        bundle.putString("producturl", weburl);
                        FragmentErp fragmentProduct1 = new FragmentErp();
                        fragmentProduct1.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentProduct1).commit();
                    }});
            }
        }


         if(savedInstanceState!=null) {
             if (images2==null) {
                 images2= savedInstanceState.getParcelableArrayList("iconlist");
                 images3=images2;
                 IconAdapter iconAdapter1 = new IconAdapter(getActivity(), images2);
                 mrecyclerview.setAdapter(iconAdapter1);
                 progressBar.setVisibility(View.INVISIBLE);
                 iconAdapter1.setOnItemClickListener(new IconAdapter.OnItemClickListener() {
                     @Override
                     public void onItemClick(int position) {
                         Icon icon=images.get(position);
                         String url=images.get(position).getUrl();
                         String iconName=images.get(position).getIcoNm();
                         if(iconName.equals("Feature")){
                             Bundle bundle = new Bundle();
                             bundle.putString("url", url);
                             bundle.putString("name", iconName);
                             AboutFragment aboutFragment = new AboutFragment();
                             aboutFragment.setArguments(bundle);
                             getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,aboutFragment ).commit();

                         }
                         else {
                             Bundle bundle = new Bundle();
                             bundle.putParcelable("icon",icon);
                             FragmentInfo fragmentinfo = new FragmentInfo();
                             fragmentinfo.setArguments(bundle);
                             getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentinfo).commit();

                         }



                         // Toast.makeText(getActivity(),"Position"+position,Toast.LENGTH_LONG).show();
                     }
                 });
             }
             if(images3==null){

                 images2 = savedInstanceState.getParcelableArrayList("iconlist2");
                 images3=images2;
                 IconAdapter iconAdapter2 = new IconAdapter(getActivity(), images2);
                 mrecyclerview.setAdapter(iconAdapter2);
                 progressBar.setVisibility(View.INVISIBLE);
                 iconAdapter2.setOnItemClickListener(new IconAdapter.OnItemClickListener() {
                     @Override
                     public void onItemClick(int position) {
                         Icon icon=images.get(position);
                         String url=images.get(position).getUrl();
                         String iconName=images.get(position).getIcoNm();
                         if(iconName.equals("Feature")){
                             Bundle bundle = new Bundle();
                             bundle.putString("url", url);
                             bundle.putString("name", iconName);
                             AboutFragment aboutFragment = new AboutFragment();
                             aboutFragment.setArguments(bundle);
                             getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,aboutFragment ).commit();

                         }
                         else {
                             Bundle bundle = new Bundle();
                             bundle.putParcelable("icon",icon);
                             FragmentInfo fragmentinfo = new FragmentInfo();
                             fragmentinfo.setArguments(bundle);
                             getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentinfo).commit();

                         }



                         // Toast.makeText(getActivity(),"Position"+position,Toast.LENGTH_LONG).show();
                     }
                 });

             }


         }








        if(MainActivity.cid1!=MainActivity.cid2){
            getIconInfo(MainActivity.cid2);
           // MainActivity.cid="0";
           // MainActivity.mid=MainActivity.cid;
            MainActivity.cid1=MainActivity.cid2;

        }


      //getDefaultData();

        return view;
    }



    public void getAppVersions(){


        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<AllVersion> allversion= apiInterface.getAllVersions();
        allversion.enqueue(new Callback<AllVersion>() {
            @Override
            public void onResponse(Call<AllVersion> call, Response<AllVersion> response) {
                vList=response.body().getVersions();
                if(vList.size()>0){

                 Version   version=vList.get(0);

                   getServerDate(version.getvDate());

                    Date  today=new Date();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat();








                }
            }

            @Override
            public void onFailure(Call<AllVersion> call, Throwable t) {

            }
        });

    }


    public void getServerDate(final String date){
        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<CurDate> currentdate= apiInterface.getCurrentDate();
        currentdate.enqueue(new Callback<CurDate>() {
            @Override
            public void onResponse(Call<CurDate> call, Response<CurDate> response) {
             String   gdate=response.body().getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date databasedate = sdf.parse(date);
                    Date serverdate = sdf.parse(gdate);


                    if(databasedate.compareTo(serverdate)==0 ){
                        // Toast.makeText(MainActivity.this,"Update Available",Toast.LENGTH_LONG).show();

                       getFragmentManager().beginTransaction().replace(R.id.container,new UpdateApps()).commit();

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Toast.makeText(MainActivity.this,"Date:"+date,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<CurDate> call, Throwable t) {

            }
        });


    }

    private void getDefaultData() {


        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<UserList> clientinfo= apiInterface.getClientInfo("cdash","888");
        clientinfo.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
              ArrayList<User>  userinfoes=response.body().getUser();
                if(!userinfoes.isEmpty()){
                    // Intent intent=new Intent(UserLogin.this,MainActivity.class);
                    User user= userinfoes.get(0);
                    MainActivity.cid=user.getCid();
                    MainActivity.cname=user.getcNm();
                    MainActivity.caddress=user.getAddress();
                    MainActivity.uname=user.getuNm();
                    MainActivity.upassword=user.getuPs();
                    MainActivity.cemail=user.getEmail();
                    MainActivity.cphone=user.getPhone();
                    MainActivity.cdate=user.getDob();
                    MainActivity.tablename=user.getTblNm();
                    MainActivity.url=user.getUrl();
                    MainActivity.featureid=user.getFID();

                    String urlimg=url+"/"+MainActivity.url;
                    //Toast.makeText(getActivity(),"value:"+MainActivity.url,Toast.LENGTH_LONG).show();
                    Picasso.get().load(urlimg).into(imageView);


                    username.setText(user.getcNm());
                    useraddress.setText(user.getAddress());




                  //  Toast.makeText(getActivity(),"You logged In Successfully"+user.getAddress(),Toast.LENGTH_LONG).show();

                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();




                    // bundle.putParcelable("obj",user);
                    // intent.putExtras(bundle);
                    // Toast.makeText(UserLogin.this,"Cid:"+userinfoes.get(0).getCid(),Toast.LENGTH_LONG).show();
                    //startActivity(intent);


                    // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment());

                }else{

                    Toast.makeText(getActivity(),"Wrong username and password",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });
    }




    private void showMessage() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new NotificationActivity()).commit();
    }

    private void senddata() {





        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new EditFragment()).commit();
    }





    private void fillArrayList() {

        employees=new ArrayList<>();
        employees.add(new Employee("01",R.drawable.p1,"product"));
        employees.add(new Employee("02",R.drawable.p2,"product"));
        employees.add(new Employee("03",R.drawable.p3,"product"));
        employees.add(new Employee("04",R.drawable.p4,"product"));
        employees.add(new Employee("05",R.drawable.p5,"product"));
        employees.add(new Employee("06",R.drawable.p6,"product"));
        employees.add(new Employee("07",R.drawable.p7,"product"));
        employees.add(new Employee("08",R.drawable.p8,"product"));
        employees.add(new Employee("09",R.drawable.p9,"product"));
        employees.add(new Employee("10",R.drawable.p10,"product"));
        employees.add(new Employee("11",R.drawable.p11,"product"));
        employees.add(new Employee("12",R.drawable.p12,"product"));
        employees.add(new Employee("13",R.drawable.p13,"product"));
        employees.add(new Employee("14",R.drawable.p14,"product"));
        employees.add(new Employee("15",R.drawable.p9,"product"));
        employees.add(new Employee("16",R.drawable.p10,"product"));
        employees.add(new Employee("17",R.drawable.p11,"product"));
        employees.add(new Employee("18",R.drawable.p12,"product"));
        employees.add(new Employee("19",R.drawable.p13,"product"));
        employees.add(new Employee("20",R.drawable.p14,"product"));

    }



     public void getIconInfo(String parms){



         ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
         Call<Allicon> icons= apiInterface.getAllIconsById(parms);
         icons.enqueue(new Callback<Allicon>() {
             @Override
             public void onResponse(Call<Allicon> call, Response<Allicon> response) {
                 images=response.body().getImages();
                 if(!images.isEmpty()){
                     IconAdapter iconAdapter=new IconAdapter(getActivity(),images);
                     mrecyclerview.setAdapter(iconAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                    iconAdapter.setOnItemClickListener(new IconAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Icon icon=images.get(position);
                            String url=images.get(position).getUrl();
                            String iconName=images.get(position).getIcoNm();
                            if(iconName.equals("Feature")){
                                Bundle bundle = new Bundle();
                                bundle.putString("url", url);
                                bundle.putString("name", iconName);
                                AboutFragment aboutFragment = new AboutFragment();
                                aboutFragment.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,aboutFragment ).commit();

                            }
                            else {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("icon",icon);
                                FragmentInfo fragmentinfo = new FragmentInfo();
                                fragmentinfo.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentinfo).commit();

                            }



                           // Toast.makeText(getActivity(),"Position"+position,Toast.LENGTH_LONG).show();
                        }
                    });

                     // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();




                     // bundle.putParcelable("obj",user);
                     // intent.putExtras(bundle);
                     // Toast.makeText(UserLogin.this,"Cid:"+userinfoes.get(0).getCid(),Toast.LENGTH_LONG).show();
                     //startActivity(intent);


                     // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment());

                 }else{

                    // Toast.makeText(getActivity(),"Wrong username and password",Toast.LENGTH_LONG).show();

                 }
             }

             @Override
             public void onFailure(Call<Allicon> call, Throwable t) {

             }
         });
     }


    private void getWebUrl(){

        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<Website> websites= apiInterface.getWebsitesUrl();
        websites.enqueue(new Callback<Website>() {
            @Override
            public void onResponse(Call<Website> call, Response<Website> response) {

                webUrls=response.body().getUrls();
                if( webUrls.size()>0){

                    if(webUrls.size()>0){

                        FirstAdapter firstAdapter=new FirstAdapter(webUrls,getActivity());
                        recyclerView1.setAdapter(firstAdapter);

                        firstAdapter.setOnItemClickListener(new FirstAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                                //String productname=employees.get(position).getName()+"-"+employees.get(position).getProductname();

                                String weburl=webUrls.get(position).getUrl();
                                Bundle bundle = new Bundle();
                                bundle.putString("producturl", weburl);
                                FragmentErp fragmentProduct1 = new FragmentErp();
                                fragmentProduct1.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentProduct1).commit();
                            }});

                        // Toast.makeText(getActivity(),"size"+webUrls.size(),Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<Website> call, Throwable t) {

            }
        });




    }
    private void jSonParsing() {
        webUrls.clear();
        // Toast.makeText(Registration.this,"json parsing calling",Toast.LENGTH_LONG).show();
         String curl="https://cdash.boraksoftware.com/getweb.php";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, curl, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray=response.getJSONArray("urls");

                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject hit=jsonArray.getJSONObject(i);
                        String iconid= hit.getString("IcoID");
                        String IcoName= hit.getString("IcoNm");
                        String iconName1= hit.getString("IcoNm1");
                        String St= hit.getString("St");
                        String TrnID= hit.getString("TrnID");
                        String weburl= hit.getString("url");

                        webUrls.add(new WebUrls(iconid,IcoName,iconName1,St,weburl));

                    }
                    if(webUrls.size()>0){

                        FirstAdapter firstAdapter=new FirstAdapter(webUrls,getActivity());
                        recyclerView1.setAdapter(firstAdapter);

                        firstAdapter.setOnItemClickListener(new FirstAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                                //String productname=employees.get(position).getName()+"-"+employees.get(position).getProductname();

                                String weburl=webUrls.get(position).getUrl();
                                Bundle bundle = new Bundle();
                                bundle.putString("producturl", weburl);
                                FragmentErp fragmentProduct1 = new FragmentErp();
                                fragmentProduct1.setArguments(bundle);

                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentProduct1).commit();
                            }});

                       // Toast.makeText(getActivity(),"size"+webUrls.size(),Toast.LENGTH_LONG).show();
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


    public  class MyPagerAdapter extends FragmentPagerAdapter {




        public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            switch (position) {

                case 0:
                    fragment = new Frag2();
                    break;


            }
            return fragment;

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:
                    return "Services";
                case 1:
                    return "About us";
                case 2:
                    return "Contact";

            }
            return null;
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putParcelableArrayList("weburls",webUrls);

        if(webUrls3!=null){
            //Toast.makeText(getActivity(),"image 2 not null",Toast.LENGTH_LONG).show();
            outState.putParcelableArrayList("weburls2",webUrls3);
        }



          outState.putParcelableArrayList("iconlist",images);

          if(images3!=null){
              //Toast.makeText(getActivity(),"image 2 not null",Toast.LENGTH_LONG).show();
              outState.putParcelableArrayList("iconlist2",images3);

          }


        super.onSaveInstanceState(outState);
    }
}
