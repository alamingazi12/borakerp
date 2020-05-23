package com.example.actionbartutorial.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.actionbartutorial.adapter.OfferAdapter;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.Offer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OfferFragment extends Fragment {

    RecyclerView offerrecyclerview;

    ArrayList<Offer> offers=new ArrayList<>();



    String curl="https://cdash.boraksoftware.com/getoffer.php";

    RequestQueue requestQueue;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.offerfragment, container, false);
        offerrecyclerview=view.findViewById(R.id.recycler_offer);
        requestQueue= Volley.newRequestQueue(getActivity());
        //Toast.makeText(getActivity(),"This is offer page" ,Toast.LENGTH_SHORT).show();
        offerrecyclerview.setHasFixedSize(true);
        offerrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        jSonParsing(view);
        return view;
    }


    private void jSonParsing(final View view) {
        // Toast.makeText(Registration.this,"json parsing calling",Toast.LENGTH_LONG).show();
          if(offers.size()>0){
              offers.clear();

          }

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
                        offers.add(new Offer(oid,ohead,body,startdate,enddate,offerstutus));
                    }
                 if(offers.size()>0){

                    // progressBar.setVisibility(View.GONE);
                     OfferAdapter offerAdapter=new OfferAdapter(getActivity(),offers);
                     offerrecyclerview.setAdapter(offerAdapter);


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
