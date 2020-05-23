package com.example.actionbartutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.model.Notification;
import com.example.actionbartutorial.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

        ArrayList<Notification> notifications;
        Context context;
    public NotificationAdapter(FragmentActivity activity, ArrayList<Notification> notifications) {
        this.notifications=notifications;
        context=activity;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {

         Notification notification= notifications.get(position);

        holder.messagedate.setText(notification.getNdate());
        holder.messagedetails.setText(notification.getNbody());
        holder.messageheading.setText(notification.getNhead());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView messagedate,messageheading,messagedetails;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            messagedate=itemView.findViewById(R.id.noti_date);
            messagedetails=itemView.findViewById(R.id.text_body);
            messageheading= itemView.findViewById(R.id.txt_heading);
        }
    }
}
