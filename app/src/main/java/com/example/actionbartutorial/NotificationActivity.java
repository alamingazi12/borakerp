package com.example.actionbartutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.adapter.NotificationAdapter;
import com.example.actionbartutorial.fragment.HomeFragment;
import com.example.actionbartutorial.model.Notification;

import java.util.ArrayList;

public class NotificationActivity extends Fragment {

    ArrayList<Notification> notificationlist;
    RecyclerView recyclerViewmessage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notification_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notificationlist= HomeFragment.notiList;
        recyclerViewmessage=view.findViewById(R.id.notificationrecyclerview);
        recyclerViewmessage.setHasFixedSize(true);
        recyclerViewmessage.setLayoutManager(new LinearLayoutManager(getActivity()));

        NotificationAdapter notificationAdapter=new NotificationAdapter(getActivity(),notificationlist);
        recyclerViewmessage.setAdapter(notificationAdapter);




    }
}
