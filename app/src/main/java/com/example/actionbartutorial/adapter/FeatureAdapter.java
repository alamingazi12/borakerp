package com.example.actionbartutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.model.Feature;
import com.example.actionbartutorial.R;

import java.util.ArrayList;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureHolder> {
    Context context;
    ArrayList<Feature> features;

    public FeatureAdapter(FragmentActivity activity, ArrayList<Feature> allfeatures) {

        this.context=activity;
        this.features=allfeatures;
    }

    @NonNull
    @Override
    public FeatureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.featureitem,parent,false);
        return new FeatureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureHolder holder, int position) {

        Feature feature=features.get(position);
        holder.featurename.setText(feature.getFNm());
        holder.description.setText(feature.getNote());
       // holder.active.setText(feature.getMod());

    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    public class FeatureHolder extends RecyclerView.ViewHolder {

        TextView featurename,description,active;
        public FeatureHolder(@NonNull View itemView) {
            super(itemView);

            featurename=itemView.findViewById(R.id.featurename);
            description=itemView.findViewById(R.id.fdescription);
            active=itemView.findViewById(R.id.featureactive);


        }
    }
}
