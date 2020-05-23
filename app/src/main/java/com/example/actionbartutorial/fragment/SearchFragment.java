package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.network.ApiClient;
import com.example.actionbartutorial.network.ApiInterface;
import com.example.actionbartutorial.adapter.OfferAdapter;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.Offer;
import com.example.actionbartutorial.model.OfferList;
import com.github.ybq.android.spinkit.style.Circle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    ArrayList<Offer> searchoffer;

 EditText textinput;
  Button search;
  RecyclerView recyclerViewsearchresult;
  ProgressBar progressBar;
   String obodytext=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar=view.findViewById(R.id.spin_kit);

        Circle circle=new Circle();
        progressBar.setIndeterminateDrawable(circle);
        progressBar.setVisibility(View.INVISIBLE);
        textinput=view.findViewById(R.id.editsearch);
        search=view.findViewById(R.id.button_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String search =textinput.getText().toString();

                getSearchContent(search);

            }
        });











        recyclerViewsearchresult=view.findViewById(R.id.searchrecycler);
        recyclerViewsearchresult.setHasFixedSize(true);
        recyclerViewsearchresult.setLayoutManager(new LinearLayoutManager(getActivity()));





    }

    private void getSearchContent(String search) {


        ApiInterface apiInterface= ApiClient.getApiClient(getActivity()).create(ApiInterface.class);
        Call<OfferList> searchinfo= apiInterface.getSearchOffer(search);
        searchinfo.enqueue(new Callback<OfferList>() {
            @Override
            public void onResponse(Call<OfferList> call, Response<OfferList> response) {
                searchoffer =response.body().getResults();

                if(searchoffer.size()>0){
                    progressBar.setVisibility(View.INVISIBLE);
                   // Toast.makeText(getActivity(),"data:"+searchoffer.get(0).getObody() ,Toast.LENGTH_LONG).show();
                    OfferAdapter offerAdapter=new OfferAdapter(getActivity(),searchoffer);
                    recyclerViewsearchresult.setAdapter(offerAdapter);
                    //searchoffer.clear();
                }
            }

            @Override
            public void onFailure(Call<OfferList> call, Throwable t) {

            }
        });
    }


}
