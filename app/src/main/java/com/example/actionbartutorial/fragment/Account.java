package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.actionbartutorial.ClearApplicationData;
import com.example.actionbartutorial.MainActivity;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.fragment.HomeFragment;
import com.example.actionbartutorial.model.User;
import com.example.actionbartutorial.model.UserList;
import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account extends Fragment {

    ArrayList<User> userinfoes;

    String cid;
    String name;

    Button loginBtn;
    EditText username,password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmmemt_account,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Toast.makeText(getActivity(),""+name,Toast.LENGTH_LONG).show();
        username=view.findViewById(R.id.clientusername);
        password=view.findViewById(R.id.clientpassword);
        loginBtn=view.findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cusername=username.getText().toString();
                String cpassword=password.getText().toString();
                ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
                Call<UserList> clientinfo= apiInterface.getClientInfo(cusername,cpassword);
                clientinfo.enqueue(new Callback<UserList>() {
                    @Override
                    public void onResponse(Call<UserList> call, Response<UserList> response) {
                        userinfoes=response.body().getUser();
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
                            MainActivity.url=user.getUrl();
                            MainActivity.tablename=user.getTblNm();
                            //MainActivity.featureid=user.FID;
                            MainActivity.featureid=user.getFID();


                            //Toast.makeText(getActivity(),"You logged In Successfully"+user.getAddress(),Toast.LENGTH_LONG).show();
                            username.setText("");
                            password.setText("");
                            ClearApplicationData.deleteCache(getActivity());
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();




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
        });
    }

    public void getvalueName(String input){


        name = input;

    }



}
