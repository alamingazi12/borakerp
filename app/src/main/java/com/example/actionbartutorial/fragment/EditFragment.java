package com.example.actionbartutorial.fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.actionbartutorial.ClearApplicationData;
import com.example.actionbartutorial.MainActivity;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.UpdateInfo;
import com.example.actionbartutorial.model.User;
import com.example.actionbartutorial.model.UserList;
import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFragment extends Fragment {
    ArrayList<User> userupdatinfo;
    User user=null;
    public EditFragment(){}
    TextView companytext;
    ImageView companyimage;
    String url="http://cdash.boraksoftware.com/comicon";
    String uemail,uphone,udate,uaddress,upass;
    EditText cemail,cphone,cdatebirth,caddress;
    TextInputLayout cpass;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.editfragment,container,false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        companyimage=view.findViewById(R.id.companyimage);
        cemail=view.findViewById(R.id.editemail);
        cphone= view.findViewById(R.id.editphone);
        cdatebirth=view.findViewById(R.id.changedate);
        caddress=view.findViewById(R.id.editaddres);
        cpass=view.findViewById(R.id.editpassword);
        companytext=view.findViewById(R.id.companyname);
        //user= getArguments().getParcelable("user");
        if(MainActivity.cid!=null){
            String urlimg=url+"/"+MainActivity.url;
            Picasso.get().load(urlimg).into(companyimage);
        }
        getInfo();
        cemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        view.findViewById(R.id.buttonupdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uemail=cemail.getText().toString();
                uphone=cphone.getText().toString();
                udate=cdatebirth.getText().toString();
                uaddress=caddress.getText().toString();
                upass=cpass.getEditText().getText().toString().trim();
                updateuser();
            }
        });


       return view;
    }



    private boolean validateEmail() {
        String email = cemail.getText().toString().trim();
        if (email.isEmpty()) {
            cemail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            cemail.setError("Please enter a valid email address");
            return false;
        } else {
            cemail.setError(null);
            return true;
        }
    }

    private void updateuser() {
        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<UpdateInfo> update=apiInterface.getupdateInfo(uaddress,uemail,uphone,udate,MainActivity.uname,upass);
        update.enqueue(new Callback<UpdateInfo>() {
            @Override
            public void onResponse(Call<UpdateInfo> call, Response<UpdateInfo> response) {
                String message=response.body().getMessage();
                if(message.equals("success")){
                    getInfochange();
                }
                else {
                    Toast.makeText(getActivity(),"Something Wrong Please Try Again",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UpdateInfo> call, Throwable t) {

            }
        });
    }



    public void getInfo(){
        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<UserList> clientinfo= apiInterface.updateClientInfo(MainActivity.uname);
        clientinfo.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                userupdatinfo=response.body().getUser();
                if(!userupdatinfo.isEmpty()){
                    user=userupdatinfo.get(0);
                    cemail.setText(user.getEmail());
                    cphone.setText(user.getPhone());
                    caddress.setText(user.getAddress());
                    cdatebirth.setText(user.getDob());
                    cpass.getEditText().setText(user.getuPs());
                    companytext.setText(user.getcNm());
                    // bundle.putParcelable("obj",user);
                    // intent.putExtras(bundle);
                    // Toast.makeText(UserLogin.this,"Cid:"+userinfoes.get(0).getCid(),Toast.LENGTH_LONG).show();
                    //startActivity(intent);
                    // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment());
                }else{
                    //  Toast.makeText(getActivity(),"Wrong username and password",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });
    }



    public void getInfochange(){
        ClearApplicationData.deleteCache(getActivity());
        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<UserList> clientinfo= apiInterface.updateClientInfo(MainActivity.uname);
        clientinfo.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                userupdatinfo=response.body().getUser();
                if(!userupdatinfo.isEmpty()){
                    User user=userupdatinfo.get(0);
                    MainActivity.cname=user.getcNm();
                    MainActivity.caddress=user.getAddress();

                    Toast.makeText(getActivity(),"You updated Successfully",Toast.LENGTH_LONG).show();
                    // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                    // Intent intent=new Intent(UserLogin.this,MainActivity.class);
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




}
