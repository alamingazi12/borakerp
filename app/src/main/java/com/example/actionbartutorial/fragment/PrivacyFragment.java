package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.adapter.PrivacyAdapter;
import com.example.actionbartutorial.model.Policies;
import com.example.actionbartutorial.model.Policy;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyFragment extends Fragment {

    ArrayList<Policy> policies;
    RecyclerView privaciesRecyclerview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.privacy_layout,container,false);
        privaciesRecyclerview=view.findViewById(R.id.privacyrecyclerview);
        privaciesRecyclerview.setHasFixedSize(true);
        privaciesRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        getPrivacyPolicy(view);

        return view;
    }


    private void getPrivacyPolicy(final View view) {
        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<Policies> clientinfo= apiInterface.getAllPolicies();
        clientinfo.enqueue(new Callback<Policies>() {
            @Override
            public void onResponse(Call<Policies> call, Response<Policies> response) {
               policies=response.body().getPolicies();
                if(!policies.isEmpty()){// Intent intent=new Intent(UserLogin.this,MainActivity.class);
                    //Feature feature= allfeatures.get(0);
                    view.findViewById(R.id.pprogress).setVisibility(View.INVISIBLE);
                    PrivacyAdapter privacyAdapter=new PrivacyAdapter(policies,getActivity());
                    privaciesRecyclerview.setAdapter(privacyAdapter);


                   // Toast.makeText(getActivity(),"You logged In Successfully"+policies.size(),Toast.LENGTH_LONG).show();

                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                }else{

                    //Toast.makeText(getActivity(),"Wrong username and password",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Policies> call, Throwable t) {

            }
        });
    }
}
