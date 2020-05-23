package com.example.actionbartutorial.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actionbartutorial.R;
import com.example.actionbartutorial.model.Transaction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {



    Context context;
    ArrayList<Transaction>  transactions;
    public TransactionAdapter(FragmentActivity activity, ArrayList<Transaction> transactionslist) {
        context=activity;
        transactions=transactionslist;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.transitem,parent,false);
        return new TransactionHolder(view);

    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {

        Random rnd = new Random();

        /*

        if(position%2==0){


            int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            //holder.cardView.setBackgroundResource(R.drawable.itembackgrounds);
            holder.cardView.setBackgroundResource(R.drawable.itembackgrounds);
        }
*/
        Transaction transaction=transactions.get(position);

        if(transaction.getSlno().equals("1")){

            holder.cardView.setBackgroundResource(R.drawable.itembackgrounds);

             String date= transaction.getTdate();
              String a[]= date.split("-");
              String year=a[0];
              String month=a[1];
              String day=a[2];
              String newdate=day+"-"+month+"-"+year;
              holder.transdate.setText(newdate);

        }





            holder.transname.setText(transaction.gettNm());
            //holder.transdate.setText(transaction.getTdate());
            String amount=transaction.getAmount();


            double amounts= Double.parseDouble(amount);
            String tamount=NumberFormat.getNumberInstance(Locale.ENGLISH).format(amounts);


            holder.transamount.setText(tamount);



    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class TransactionHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView transname,transdate,transamount;
        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            cardView= itemView.findViewById(R.id.itemcardview);
            transname=itemView.findViewById(R.id.tname);
            transdate=itemView.findViewById(R.id.tdate);
            transamount=itemView.findViewById(R.id.tamount);
        }
    }
}
