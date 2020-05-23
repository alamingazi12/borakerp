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

import com.example.actionbartutorial.model.Employee;
import com.example.actionbartutorial.MainActivity;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.WebUrls;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ExampleViewHolder> {

    ArrayList<WebUrls> urls;
    Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public FirstAdapter(ArrayList<WebUrls> urls, Context context) {
        this.context = context;
        this.urls = urls;
    }



    public FirstAdapter(MainActivity mainActivity, ArrayList<Employee> employees) {

    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new ExampleViewHolder(view,mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder holder, int position) {

        String urlimage="https://cdash.boraksoftware.com/webicon/";
        String extension=".jpg";

          WebUrls url=urls.get(position);
          holder.name.setText(url.getIcoNm());
          holder.productname.setText(url.getIcoNm1());
          String iurl=urlimage+url.getIcoID()+extension;
        // holder.imageView.setImageResource(iurl);
        Picasso.get()
                .load(iurl)
                .resize(130,120)
                .error(R.drawable.borak)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.productprogress.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        TextView name,productname;
        ImageView imageView;
        ProgressBar productprogress;

        public ExampleViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            name= itemView.findViewById(R.id.imagename);
            imageView=itemView.findViewById(R.id.imageView);
            productname=itemView.findViewById(R.id.textproductname);
            productprogress=itemView.findViewById(R.id.progressproduct);

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
