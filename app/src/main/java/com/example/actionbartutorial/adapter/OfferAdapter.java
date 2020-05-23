package com.example.actionbartutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.Offer;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    ArrayList<Offer> offers;
    Context context;

    public OfferAdapter(FragmentActivity activity, ArrayList<Offer> offers) {
        this.offers=offers;
        this.context=activity;
    }

    @NonNull
    @Override
    public OfferAdapter.OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.offer_item,parent,false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.OfferViewHolder holder, int position) {

          Offer offer=offers.get(position);
          holder.heading.setText(offer.getOhead());
          holder.offerbody.setText(offer.getObody());

          holder.enddate.setText("Valid until: "+offer.getOenddate());



    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder {

        TextView heading,startdate,enddate,offerbody;
        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);

            heading=itemView.findViewById(R.id.offer_heading);

            enddate=itemView.findViewById(R.id.end_date);
            offerbody=itemView.findViewById(R.id.offer_body);
        }
    }
}
