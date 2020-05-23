package com.example.actionbartutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.GroupItem;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {
    ArrayList<GroupItem> items;
    int row;
    Context context;
    public NestedAdapter(Context firstAdapter, ArrayList<GroupItem> items, int row) {
       context=firstAdapter;
       this.row=row;
       this.items=items;
    }

    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        if(row==1){

             view = LayoutInflater.from(context).inflate(R.layout.heading, parent, false);
          //return new NestedViewHolder(view);
        }
        else if(row==2){
          view = LayoutInflater.from(context).inflate(R.layout.heading2, parent, false);
           // return new NestedViewHolder(view);
        }
        else if(row==3){
           view = LayoutInflater.from(context).inflate(R.layout.heading3, parent, false);
            //return new NestedViewHolder(view);
        }
        else if(row==4){
          view = LayoutInflater.from(context).inflate(R.layout.dashboard, parent, false);
          //  return new NestedViewHolder(view);
        }
        else if(row==5){
            view = LayoutInflater.from(context).inflate(R.layout.heading5, parent, false);
            //  return new NestedViewHolder(view);
        }
        else if(row==6){
            view = LayoutInflater.from(context).inflate(R.layout.heading6, parent, false);
            //  return new NestedViewHolder(view);
        }
        else if(row==7){
            view = LayoutInflater.from(context).inflate(R.layout.heading7, parent, false);
            //  return new NestedViewHolder(view);
        }
        else if(row==8){
            view = LayoutInflater.from(context).inflate(R.layout.heading8, parent, false);
            //  return new NestedViewHolder(view);
        }
        return new NestedViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
        GroupItem groupItem=items.get(position);

        if(groupItem!=null){


            if(row==1){
                holder.tname.setText(groupItem.getDescription());

            }

           else if(row==2){

                holder.tname.setText(groupItem.getDescription());
                holder.amount.setText(groupItem.getAmount());
            }

            else if(row==3){
                holder.tname.setText(groupItem.getDescription());
                holder.amount.setText(groupItem.getAmount());
                holder.amount1.setText(groupItem.getNote1());


            }

            else if(row==4){

                holder.tname.setText(groupItem.getDescription());
                holder.amount.setText(groupItem.getAmount());
                holder.amount1.setText(groupItem.getAmount1());
                holder.amount2.setText(groupItem.getAmount2());

            }

            else if(row==5){

                holder.tname.setText(groupItem.getDescription());
                holder.amount.setText(groupItem.getAmount());
                holder.amount1.setText(groupItem.getAmount1());
                holder.amount2.setText(groupItem.getAmount2());
                holder.amount3.setText(groupItem.getAmount3());

            }

            else if(row==6){

                holder.tname.setText(groupItem.getDescription());
                holder.amount.setText(groupItem.getAmount());
                holder.amount1.setText(groupItem.getAmount1());
                holder.amount2.setText(groupItem.getAmount2());
                holder.note.setText(groupItem.getNote1());
                holder.note2.setText(groupItem.getNote2());
            }

            else if(row==7){

                holder.tname.setText(groupItem.getDescription());
                holder.amount.setText(groupItem.getAmount());
                holder.amount1.setText(groupItem.getAmount1());
                holder.amount2.setText(groupItem.getAmount2());
                holder.amount3.setText(groupItem.getAmount3());
                holder.note.setText(groupItem.getNote1());
                holder.note2.setText(groupItem.getNote2());
            }
            else if(row==8){

                holder.tname.setText(groupItem.getDescription());
                holder.amount.setText(groupItem.getAmount());
                holder.amount1.setText(groupItem.getAmount1());
                holder.amount2.setText(groupItem.getAmount2());
                holder.amount3.setText(groupItem.getAmount3());
                holder.amount4.setText(groupItem.getAmount4());
                holder.note.setText(groupItem.getNote1());
                holder.note2.setText(groupItem.getNote2());
            }


        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder {

        TextView tname,amount,amount1,amount2,amount4,amount3,note,note2;
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);

            if(row==1)
            {
                tname=itemView.findViewById(R.id.tname);
            }
            else if(row==2){
                tname=itemView.findViewById(R.id.tname);
                amount=itemView.findViewById(R.id.amount);


            }

            else if(row==3){

                tname=itemView.findViewById(R.id.tname);
                amount=itemView.findViewById(R.id.amount);
                amount1=itemView.findViewById(R.id.note);

            }
            else if(row==4){
                tname=itemView.findViewById(R.id.tname);
                amount=itemView.findViewById(R.id.amount);
                amount1=itemView.findViewById(R.id.amount1);
                amount2=itemView.findViewById(R.id.amount2);

            }
            else if(row==5){
                tname=itemView.findViewById(R.id.tname);
                amount=itemView.findViewById(R.id.amount);
                amount1=itemView.findViewById(R.id.amount1);
                amount2=itemView.findViewById(R.id.amount2);
                amount3=itemView.findViewById(R.id.amount3);

            }
            else if(row==6){
                tname=itemView.findViewById(R.id.tname);
                amount=itemView.findViewById(R.id.amount);
                amount1=itemView.findViewById(R.id.amount1);
                amount2=itemView.findViewById(R.id.amount2);

                note=itemView.findViewById(R.id.note);
                note2=itemView.findViewById(R.id.note2);

            }
            else if(row==7){
                tname=itemView.findViewById(R.id.tname);
                amount=itemView.findViewById(R.id.amount);
                amount1=itemView.findViewById(R.id.amount1);
                amount2=itemView.findViewById(R.id.amount2);
                amount3=itemView.findViewById(R.id.amount3);
                note=itemView.findViewById(R.id.note);
                note2=itemView.findViewById(R.id.note2);
            }
            else if(row==8){
                tname=itemView.findViewById(R.id.tname);
                amount=itemView.findViewById(R.id.amount);
                amount1=itemView.findViewById(R.id.amount1);
                amount2=itemView.findViewById(R.id.amount2);
                amount3=itemView.findViewById(R.id.amount3);
                amount4=itemView.findViewById(R.id.amount4);
                note=itemView.findViewById(R.id.note);
                note2=itemView.findViewById(R.id.note2);
            }


        }
    }
}
