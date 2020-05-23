package com.example.actionbartutorial.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.model.GroupTransaction;
import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.Icon;

public class FirstTransactionAdapter extends RecyclerView.Adapter<FirstTransactionAdapter.ExampleAdapter> {
    int row;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Icon icon;
    ArrayList<GroupTransaction> groups;
    Context context;
    public FirstTransactionAdapter(Context mainActivity, String hct, Icon icon) {
        this.icon=icon;

       this.row=Integer.parseInt(hct);
       context=mainActivity;
    }
   public void setGroups(ArrayList<GroupTransaction> groups){
       this.groups= groups;

    }

    @NonNull
    @Override
    public ExampleAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        if(row==1){

             view = LayoutInflater.from(context).inflate(R.layout.transactionitem1, parent, false);

        }
        else if(row==2){

            view = LayoutInflater.from(context).inflate(R.layout.transactionitem2, parent, false);


        }
        else if(row==3){

            view = LayoutInflater.from(context).inflate(R.layout.transactionitem3, parent, false);

        }
        else if(row==4){

            view = LayoutInflater.from(context).inflate(R.layout.transactionitem4, parent, false);

        }
        else if(row==5){

            view = LayoutInflater.from(context).inflate(R.layout.transactionitem5, parent, false);

        }
        else if(row==6){

            view = LayoutInflater.from(context).inflate(R.layout.transactionitem6, parent, false);

        }
        else if(row==7){

            view = LayoutInflater.from(context).inflate(R.layout.transactionitem7, parent, false);

        }
        else if(row==8){

            view = LayoutInflater.from(context).inflate(R.layout.transactionitem8, parent, false);

        }

        return new ExampleAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter holder, int position) {

        GroupTransaction group=groups.get(position);


         if(row ==1){

             String   datearr[]=group.getDate().split("-");
             int month=Integer.parseInt(datearr[1]);
             String monthName=getMonthName(month);
             String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];



            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
             Log.d("sizeof",String.valueOf(group.getItems().size()));
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }

        else if(row ==2){

             String   datearr[]=group.getDate().split("-");
             int month=Integer.parseInt(datearr[1]);
             String monthName=getMonthName(month);
             String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];

            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            holder.head2.setText(icon.getHead2());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
            Log.d("sizeof",String.valueOf(group.getItems().size()));
             Toast.makeText(context,"size is:"+group.getItems().size(),Toast.LENGTH_LONG).show();
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }

        if(row==3){

            String   datearr[]=group.getDate().split("-");
            int month=Integer.parseInt(datearr[1]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];

            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            holder.head2.setText(icon.getHead2());
            holder.head7.setText(icon.getHead7());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
            Log.d("size",String.valueOf(group.getItems().size()));
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }
        else if(row ==4){

        String   datearr[]=group.getDate().split("-");
         int month=Integer.parseInt(datearr[1]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];

            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            holder.head2.setText(icon.getHead2());
            holder.head3.setText(icon.getHead3());
            holder.head4.setText(icon.getHead4());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
            Log.d("size",String.valueOf(group.getItems().size()));
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }

        else if(row ==5){

            String   datearr[]=group.getDate().split("-");
            int month=Integer.parseInt(datearr[1]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];

            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            holder.head2.setText(icon.getHead2());
            holder.head3.setText(icon.getHead3());
            holder.head4.setText(icon.getHead4());
            holder.head5.setText(icon.getHead5());


            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }

        else if(row ==6){

            String   datearr[]=group.getDate().split("-");
            int month=Integer.parseInt(datearr[1]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];

            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            holder.head2.setText(icon.getHead2());
            holder.head3.setText(icon.getHead3());
            holder.head4.setText(icon.getHead4());

            holder.head7.setText(icon.getHead7());
            holder.head8.setText(icon.getHead8());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }

        else if(row ==7){

            String   datearr[]=group.getDate().split("-");
            int month=Integer.parseInt(datearr[1]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];


            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            holder.head2.setText(icon.getHead2());
            holder.head3.setText(icon.getHead3());
            holder.head4.setText(icon.getHead4());
            holder.head5.setText(icon.getHead5());
            holder.head7.setText(icon.getHead7());
            holder.head8.setText(icon.getHead8());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }

        else if(row ==8){

            String   datearr[]=group.getDate().split("-");
            int month=Integer.parseInt(datearr[1]);
            String monthName=getMonthName(month);
            String datewithmonth=datearr[2]+"-"+monthName+"-"+datearr[0];

            holder.date.setText("Date: "+datewithmonth);
            holder.head1.setText(icon.getHead1());
            holder.head2.setText(icon.getHead2());
            holder.head3.setText(icon.getHead3());
            holder.head4.setText(icon.getHead4());
            holder.head5.setText(icon.getHead5());
            holder.head6.setText(icon.getHead6());
            holder.head7.setText(icon.getHead7());
            holder.head8.setText(icon.getHead8());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            linearLayoutManager.setInitialPrefetchItemCount(group.getItems().size());
            holder.nestedrecycleview.setHasFixedSize(true);
            holder.nestedrecycleview.setLayoutManager(linearLayoutManager);
            NestedAdapter nestedAdapter=new NestedAdapter(context,group.getItems(),row);
            holder.nestedrecycleview.setAdapter(nestedAdapter);
            holder.nestedrecycleview.setRecycledViewPool(viewPool);

        }


    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ExampleAdapter extends RecyclerView.ViewHolder {

        TextView date,head1,head2,head3,head4,head5,head6,head7,head8;
        RecyclerView nestedrecycleview;
        public ExampleAdapter(@NonNull View itemView) {
            super(itemView);


            View view=null;
            if(row==1){

                head1=itemView.findViewById(R.id.head1);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);

            }
            else if(row==2){
                head1=itemView.findViewById(R.id.head1);
                head2=itemView.findViewById(R.id.head2);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);
            }
            else if(row==3){
                head1=itemView.findViewById(R.id.head1);
                head2=itemView.findViewById(R.id.head2);
                head7=itemView.findViewById(R.id.head7);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);
            }

            else if(row==4){
                head1=itemView.findViewById(R.id.head1);
                head2=itemView.findViewById(R.id.head2);
                head3=itemView.findViewById(R.id.head3);
                head4=itemView.findViewById(R.id.head4);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);
            }

            else if(row==5){
                head1=itemView.findViewById(R.id.head1);
                head2=itemView.findViewById(R.id.head2);
                head3=itemView.findViewById(R.id.head3);
                head4=itemView.findViewById(R.id.head4);
                head5=itemView.findViewById(R.id.head5);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);
            }
            else if(row==6){
                head1=itemView.findViewById(R.id.head1);
                head2=itemView.findViewById(R.id.head2);
                head3=itemView.findViewById(R.id.head3);
                head4=itemView.findViewById(R.id.head4);
                head7=itemView.findViewById(R.id.head7);
                head8=itemView.findViewById(R.id.head8);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);
            }
            else if(row==7){
                head1=itemView.findViewById(R.id.head1);
                head2=itemView.findViewById(R.id.head2);
                head3=itemView.findViewById(R.id.head3);
                head4=itemView.findViewById(R.id.head4);
                head5=itemView.findViewById(R.id.head5);
                head7=itemView.findViewById(R.id.head7);
                head8=itemView.findViewById(R.id.head8);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);
            }
            else if(row==8){
                head1=itemView.findViewById(R.id.head1);
                head2=itemView.findViewById(R.id.head2);
                head3=itemView.findViewById(R.id.head3);
                head4=itemView.findViewById(R.id.head4);
                head5=itemView.findViewById(R.id.head5);
                head6=itemView.findViewById(R.id.head6);
                head7=itemView.findViewById(R.id.head7);
                head8=itemView.findViewById(R.id.head8);
                date=itemView.findViewById(R.id.itemdate);
                nestedrecycleview=itemView.findViewById(R.id.nestedrecyclerview);
            }








        }
    }

    private static String getMonthName(int month) {

        String monthName = null;
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;

            case 10:
                monthName = "October";
                break;

            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "November";
                break;


        }

        return monthName;
    }
}
