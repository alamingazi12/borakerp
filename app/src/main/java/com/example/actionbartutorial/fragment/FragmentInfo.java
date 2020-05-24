package com.example.actionbartutorial.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.actionbartutorial.model.AllTransactions;
import com.example.actionbartutorial.model.Amount;
import com.example.actionbartutorial.DatePickerFragment;
import com.example.actionbartutorial.model.Details;
import com.example.actionbartutorial.model.InfoList;
import com.example.actionbartutorial.MainActivity;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.adapter.FirstTransactionAdapter;
import com.example.actionbartutorial.adapter.MyAdapter;
import com.example.actionbartutorial.model.GroupItem;
import com.example.actionbartutorial.model.GroupTransaction;
import com.example.actionbartutorial.model.Icon;
import com.example.actionbartutorial.model.Transaction;
import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class FragmentInfo  extends Fragment  {
   String imageurl=null,totalamount=null;
    Boolean boolean_folder=false;



   ArrayList<Transaction> transactionslist=null;


    public static ArrayList<String> projects=new ArrayList<>();

    public static final int REQUEST_CODE1 = 11;
    public static final int REQUEST_CODE2 = 12;
    TextView editoompanyname, editcompanyaddress, iconcode,texttotalamount;
    ImageButton picdate,getenddate;
    String pcode = null;
    String cid;
    ImageView profileimage;
    String date=null;

   static int  flag=0;

    Button finddatabtn;
    TextView group1, group2;
    ArrayList<Details> details1;

    SwipeRefreshLayout myswiperefreshlayot;

    ArrayList<GroupTransaction> groups=new ArrayList<>();
    ArrayList<GroupTransaction> groups2=new ArrayList<>();
    ArrayList<GroupTransaction> groups3=new ArrayList<>();
  //  ArrayList<GroupItem> al_path=new ArrayList<>();
    Icon icon;
    RecyclerView recyclerView;
   static EditText textdate=null,enddatetext=null;
    public static String url="https://cdash.boraksoftware.com/groups.php";
    private RequestQueue mRequestownerQueue;
    ProgressBar progressBar;

    public FragmentInfo() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentinfo, container, false);
        progressBar=view.findViewById(R.id.infoprogress);
        mRequestownerQueue = Volley.newRequestQueue(getActivity());
        profileimage=view.findViewById(R.id.profiles);
        picdate = view.findViewById(R.id.getdate);
        getenddate=view.findViewById(R.id.getendate);
        enddatetext=view.findViewById(R.id.enddatetext);
        final FragmentManager fm = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        editoompanyname = view.findViewById(R.id.editsearchname);
        editcompanyaddress = view.findViewById(R.id.editaddress);
        textdate = view.findViewById(R.id.datetext);
        texttotalamount=view.findViewById(R.id.texttotalamount);
        finddatabtn=view.findViewById(R.id.go_action);
        myswiperefreshlayot=view.findViewById(R.id.myrefreshlayout);
        recyclerView = view.findViewById(R.id.trecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        editcompanyaddress.setText(MainActivity.caddress);
        editoompanyname.setText(MainActivity.cname);

        icon=getArguments().getParcelable("icon");
        pcode = icon.getIcoNm();
        imageurl = icon.getUrl();




        if(textdate.getText().toString().equals("")){

          //  Log.d("paramss",String.valueOf(textdate.getText()));
            Date today = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String newdate = simpleDateFormat.format(today);
            String a[]=newdate.split("-");
            String monthname=getMonthName(Integer.parseInt(a[1]));
            String date=a[2]+"-"+monthname+"-"+a[0];
            textdate.setText(date);
            enddatetext.setText(date);
        }

           findTransaction();
           finddatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               progressBar.setVisibility(View.VISIBLE);
               findTransaction();
            }


            });

        if(savedInstanceState!=null){
            if(groups2==null){

                groups2=savedInstanceState.getParcelableArrayList("list");
                groups3=groups2;
                FirstTransactionAdapter firstTransactionAdapter=new FirstTransactionAdapter(getActivity(),icon.getHct(),icon);
                firstTransactionAdapter.setGroups(groups2);
                recyclerView.setAdapter(firstTransactionAdapter);
            }
            if(groups3==null){
                groups2=savedInstanceState.getParcelableArrayList("list2");
                groups3=groups2;
                FirstTransactionAdapter firstTransactionAdapter=new FirstTransactionAdapter(getActivity(),icon.getHct(),icon);
                firstTransactionAdapter.setGroups(groups2);
                recyclerView.setAdapter(firstTransactionAdapter);
            }
        }

        myswiperefreshlayot.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findTransaction();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myswiperefreshlayot.setRefreshing(false);
                    }
                },400);
            };
        });

            picdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatDialogFragment newFragment = new DatePickerFragment();
                    newFragment.setTargetFragment(FragmentInfo.this, REQUEST_CODE1);
                    newFragment.show(fm, "datePicker");
                }
            });
        getenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(FragmentInfo.this, REQUEST_CODE2);
                newFragment.show(fm, "datePicker");
            }
        });
        icon=getArguments().getParcelable("icon");
        pcode = icon.getIcoNm();
        imageurl = icon.getUrl();
        if(Integer.parseInt(icon.getHct())>4){
            setOrientation();
        }

        if(imageurl!=null){
            String baseurl="http://cdash.boraksoftware.com/comicon/";
            String urlimg=baseurl+"/"+MainActivity.url;
            Picasso.get().load(urlimg).into(profileimage);
        }
            iconcode = view.findViewById(R.id.texticoncode);
            iconcode.setText(pcode);
            group2 = view.findViewById(R.id.groupname2);
            firstInitialize(view);
            return view;
        }



      public void   setOrientation(){

          getActivity().setRequestedOrientation(
                  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("list",groups);
        if(groups3!=null){
            //Toast.makeText(getActivity(),"image 2 not null",Toast.LENGTH_LONG).show();
            outState.putParcelableArrayList("list2",groups3);

            outState.putString("sdate",textdate.getText().toString());
            outState.putString("endate",enddatetext.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void findTransaction() {
        if(transactionslist!=null){
            transactionslist=null;
        }
         String cid=MainActivity.cid;
         final String tdate=textdate.getText().toString();
         String a1[]=tdate.split("-");
         String month1=getMonthString(a1[1]);
         final String stdate=a1[2]+"-"+month1+"-"+a1[0];
         final String edate=enddatetext.getText().toString();
         String a2[]=edate.split("-");
         String month2=getMonthString(a2[1]);
         final String enddate=a2[2]+"-"+month2+"-"+a2[0];

        Log.d("params",MainActivity.tablename);
        Log.d("params",stdate);
        Log.d("params",enddate);
        Log.d("params",pcode);
         // Toast.makeText(getActivity(),"end date"+enddate,Toast.LENGTH_LONG).show();
         ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
         Call<AllTransactions> clientinfo= apiInterface.getTransactions(MainActivity.tablename,stdate,enddate,pcode);
         clientinfo.enqueue(new Callback<AllTransactions>() {
            @Override
            public void onResponse(Call<AllTransactions> call, retrofit2.Response<AllTransactions> response) {
                transactionslist =response.body().getUsers();

                if(!transactionslist.isEmpty()){
                   progressBar.setVisibility(View.INVISIBLE);
                  getAllgroups(transactionslist);
                  FirstTransactionAdapter firstTransactionAdapter=new FirstTransactionAdapter(getActivity(),icon.getHct(),icon);
                  firstTransactionAdapter.setGroups(groups);
                  recyclerView.setAdapter(firstTransactionAdapter);

                  //TransactionAdapter transactionAdapter=new TransactionAdapter(getActivity(),transactionslist);

                 // recyclerView.setAdapter(transactionAdapter);
                  //getSumofAmounts(stdate,enddate,pcode);
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                  //  Toast.makeText(getActivity(),"Wrong username and password",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AllTransactions> call, Throwable t) {

            }
        });
    }



    private void getSumofAmounts(String stdate,String enddate,String pcode) {
        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<Amount> clientamount= apiInterface.getAmounts(MainActivity.tablename,stdate,enddate,pcode);
        clientamount.enqueue(new Callback<Amount>() {
            @Override
            public void onResponse(Call<Amount> call, retrofit2.Response<Amount> response) {
                totalamount=response.body().getValue_sum();
                if(!totalamount.isEmpty()){
                   double tamoount  =Double.parseDouble(totalamount);
                  String finalnumber= NumberFormat.getNumberInstance(Locale.ENGLISH).format(tamoount);
                    texttotalamount.setVisibility(View.VISIBLE);
                    texttotalamount.setText("Total Amount: "+finalnumber);
                }else{

                   // Toast.makeText(getActivity(),"Value is:"+totalamount,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Amount> call, Throwable t) {

            }
        });
    }



    private String getEditdate(String toString) {
        String datearray[] = toString.split("-");
        String year = datearray[0];
        String month = datearray[1];
        int day = Integer.parseInt(datearray[2]) + 1;
        String stday = String.valueOf(day);
        String dateString=year+"-"+month+"-"+stday;
        return dateString;
    }

    @Override
    public void onResume() {

        progressBar.setVisibility(View.VISIBLE);
        findTransaction();
        super.onResume();
    }

    private void firstInitialize (final View view){
            Call<InfoList> infoList=null;
            ApiInterface apiInterface = ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
            if (date != null) {
                infoList = apiInterface.getInfoList(date, cid, pcode);
            } else {
                Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016
                // don't forget this if date is arbitrary e.g. 01-01-2014
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String newdate = simpleDateFormat.format(today);
                infoList = apiInterface.getInfoList(newdate, cid, pcode);
            }
            infoList.enqueue(new Callback<InfoList>() {
                @Override
                public void onResponse(Call<InfoList> call, retrofit2.Response<InfoList> response) {
                    response.body().getDetails();

                    if (!details1.isEmpty()) {
                        Details details = details1.get(0);
                        group1.setText(details.getName());
                        MyAdapter myAdapter = new MyAdapter(details1, getActivity());
                        recyclerView.setAdapter(myAdapter);
                    } else {
                        Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<InfoList> call, Throwable t) {

                }
            });
        }




    private void jSonParsing() {
        projects.clear();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url,null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("results");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject hit=jsonArray.getJSONObject(i);
                        String code= hit.getString("code");
                        String Name= hit.getString("Name");
                        projects.add(Name);

                    }
                    if(projects.size()>0){
                     //   Toast.makeText(getActivity(),"json parsing calling"+projects.size(),Toast.LENGTH_LONG).show();
                    }else {

                        //Toast.makeText(getActivity(),"No Data found"+projects.size(),Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Toast.makeText(getActivity(),"something wrong"+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        mRequestownerQueue.add(jsonObjectRequest);
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE1 && resultCode == Activity.RESULT_OK) {
            date = data.getStringExtra("selectedDate");
            String datearr[]=  date.split("/");
            int month=Integer.parseInt(datearr[0]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[1]+"-"+monthName+"-"+datearr[2];
           // String newenddate=a1[1]+"-"+a1[0]+"-"+a1[2];

            textdate.setText(datewithmonth);
        }
       else if (requestCode == REQUEST_CODE2 && resultCode == Activity.RESULT_OK) {
            date = data.getStringExtra("selectedDate");
            String datearr[]=  date.split("/");
            int month=Integer.parseInt(datearr[0]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[1]+"-"+monthName+"-"+datearr[2];

           // String newenddate=a1[1]+"-"+a1[0]+"-"+a1[2];
            enddatetext.setText(datewithmonth);
        }

    }




    private ArrayList<GroupTransaction> getAllgroups(ArrayList<Transaction> transactions) {

        // Toast.makeText(getActivity(),"grups size"+groups.size(),Toast.LENGTH_LONG).show();
        if(groups.size()>0){
            groups.clear();
        }
         if(groups.size()>0 && boolean_folder==true){
             Log.d("size",String.valueOf(groups.size()));
             Toast.makeText(getActivity(),"this is not null",Toast.LENGTH_LONG).show();
             groups.clear();
             boolean_folder=false;
         }
        for(Transaction transaction:transactions){
            int position = 0;
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getDate().equals(transaction.getTdate())) {
                    boolean_folder = true;
                    position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }

            if (boolean_folder) {
                ArrayList<GroupItem> al_path = new ArrayList<>();
                al_path.addAll(groups.get(position).getItems());
                al_path.add(new GroupItem(transaction.gettNm(),transaction.getAmount(),transaction.getAmount1(),transaction.getAmount2(),transaction.getAmount3(),transaction.getAmount4(),transaction.getSlno(),transaction.getNote1(),transaction.getNote2()));
                groups.get(position).setItems(al_path);
            } else {
                ArrayList<GroupItem> al_path2 = new ArrayList<>();
                /*
                if(al_path2.size()>0){
                    al_path2.clear();
                }

                 */
                al_path2.add(new GroupItem(transaction.gettNm(),transaction.getAmount(),transaction.getAmount1(),transaction.getAmount2(),transaction.getAmount3(),transaction.getAmount4(),transaction.getSlno(),transaction.getNote1(),transaction.getNote2()));
                GroupTransaction group = new GroupTransaction();
                group.setDate(transaction.getTdate());
                group.setItems(al_path2);
                groups.add(group);
            }
        }
        return groups;
    }


    private static String getMonthName(int month) {

        String monthName = null;
        switch (month) {
            case 1:
                monthName = "Jan";
                break;
            case 2:
                monthName = "Feb";
                break;
            case 3:
                monthName = "Mar";
                break;
            case 4:
                monthName = "Apr";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "Jun";
                break;
            case 7:
                monthName = "Jul";
                break;
            case 8:
                monthName = "Aug";
                break;
            case 9:
                monthName = "Sep";
                break;

            case 10:
                monthName = "October";
                break;

            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "November";
                break;


        }

        return monthName;
    }


    private static String getMonthString(String month) {

        String monthName=null;
        switch (month) {
            case "Jan":
                monthName = "01";
                break;
            case "Feb":
                monthName = "02";
                break;
            case "Mar":
                monthName = "03";
                break;
            case "Apr":
                monthName = "04";
                break;
            case "May":
                monthName = "05";
                break;
            case "Jun":
                monthName = "06";
                break;
            case "Jul":
                monthName = "07";
                break;
            case "Aug":
                monthName = "08";
                break;
            case "Sep":
                monthName = "09";
                break;

            case "Oct":
                monthName = "10";
                break;

            case "Nov":
                monthName = "11";
                break;
            case "Dec":
                monthName = "12";
                break;


        }

        return monthName;
    }


}