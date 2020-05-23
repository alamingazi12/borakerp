package com.example.actionbartutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.Policy;

import java.util.ArrayList;

public class PrivacyAdapter  extends RecyclerView.Adapter<PrivacyAdapter.PrivacyHolder> {

    public PrivacyAdapter(ArrayList<Policy> policies, Context context) {
        this.policies = policies;
        this.context = context;
    }

    ArrayList<Policy> policies;
    Context context;
    @NonNull
    @Override
    public PrivacyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemprivacy,parent,false);
        return new PrivacyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrivacyHolder holder, int position) {

        Policy policy=policies.get(position);
//        holder.pname.setText(policy.getFNm());
        holder.pdescription.setText(policy.getNote().trim());

    }

    @Override
    public int getItemCount() {
        return policies.size();
    }

    public class PrivacyHolder extends RecyclerView.ViewHolder {

        TextView pname,pdescription,pactive;
        public PrivacyHolder(@NonNull View itemView) {
            super(itemView);

            pdescription=itemView.findViewById(R.id.pdescription);

        }
    }
}
