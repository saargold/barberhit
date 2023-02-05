package com.example.barbershophit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  {

    private List<Service> dataSet;

    public CustomAdapter(List<Service> dataSet) {

        this.dataSet = dataSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        CardView cardView;
        TextView textViewTitle;
        TextView textViewPrice;
        TextView textViewDate;


        public MyViewHolder (View itemView )
        {
            super(itemView);

            cardView =  itemView.findViewById(R.id.card_view);
            textViewTitle =  itemView.findViewById(R.id.textViewNameForCard);
            textViewPrice =  itemView.findViewById(R.id.textViewForPrice);
            textViewDate =  itemView.findViewById(R.id.textViewForDate);



        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext() ).inflate(R.layout.cards_layout , parent ,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int listPosition) {

        TextView textViewName = viewHolder.textViewTitle;
        TextView textViewPrice = viewHolder.textViewPrice;
        TextView textViewDate = viewHolder.textViewDate;
        CardView cardView = viewHolder.cardView;
        textViewName.setText(dataSet.get(listPosition).getTitle());
        textViewPrice.setText("Price: "+ dataSet.get(listPosition).getPrice()+"");




       // textViewDate.setText(dataSet.get(listPosition).getTime()+"");



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}


