package com.example.actionbartutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.Icon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.TransactionHolder> {



    Context context;
    ArrayList<Icon>  transactions;
    public IconAdapter(Context activity, ArrayList<Icon> transactionslist) {
        context=activity;
        transactions=transactionslist;
    }
    private IconAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(IconAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemicon,parent,false);
        //return new TransactionHolder(view);
        return new IconAdapter.TransactionHolder(view,mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull final TransactionHolder holder, final int position) {





        Icon icon=transactions.get(position);
        String appurl="http://cdash.boraksoftware.com/appicon/"+icon.getUrl();
        //Log.d("url",icon.getUrl());

        holder.name.setText(icon.getIcoNm());
        //((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace()
/*
            Glide.with(context)//1
                    .load(appurl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.back)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .onlyRetrieveFromCache(true)

                   //4
                    .into(holder.myimage);

 */



        Picasso.get()
                .load(appurl)
                .error(R.drawable.a)
                .into(holder.myimage);




/*


        Picasso.with(context).load(appurl).centerCrop().resize(50,50)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.myimage);

 */





    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class TransactionHolder extends RecyclerView.ViewHolder {
        ImageView myimage;
        TextView name;
        ProgressBar progressBar;
        public TransactionHolder(@NonNull View itemView,final IconAdapter.OnItemClickListener listener) {
            super(itemView);
            myimage=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
