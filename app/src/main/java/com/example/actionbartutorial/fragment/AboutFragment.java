package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.model.AllFeatures;
import com.example.actionbartutorial.model.Feature;
import com.example.actionbartutorial.MainActivity;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.adapter.FeatureAdapter;
import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutFragment extends Fragment {

    RecyclerView recyclerViewFeatures;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.aboutfragment,container,false);
        recyclerViewFeatures=view.findViewById(R.id.featurerecyclerview);
        recyclerViewFeatures.setHasFixedSize(true);
        recyclerViewFeatures.setLayoutManager(new LinearLayoutManager(getActivity()));

        getDefaultData(view);
        return view;
    }

    private void getDefaultData(final View view) {


        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<AllFeatures> clientinfo= apiInterface.getAllFeatures(MainActivity.featureid);
        clientinfo.enqueue(new Callback<AllFeatures>() {
            @Override
            public void onResponse(Call<AllFeatures> call, Response<AllFeatures> response) {
                ArrayList<Feature> allfeatures=response.body().getFeatures();
                if(!allfeatures.isEmpty()){// Intent intent=new Intent(UserLogin.this,MainActivity.class);
                    //Feature feature= allfeatures.get(0);
                     view.findViewById(R.id.feature_kit).setVisibility(View.INVISIBLE);
                    FeatureAdapter featureAdapter=new FeatureAdapter(getActivity(),allfeatures);

                    recyclerViewFeatures.setAdapter(featureAdapter);



                     //Toast.makeText(getActivity(),"You logged In Successfully"+allfeatures.size(),Toast.LENGTH_LONG).show();

                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                }else{

                    Toast.makeText(getActivity(),"Wrong username and password",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<AllFeatures> call, Throwable t) {

            }
        });
    }
}
