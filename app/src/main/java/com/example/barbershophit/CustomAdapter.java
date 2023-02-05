package com.example.barbershophit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        TextView textViewBarberName;
        TextView textViewBarberLocation;
        TextView textViewBarberPhone;


        public MyViewHolder (View itemView )
        {
            super(itemView);

            cardView =  itemView.findViewById(R.id.card_view);
            textViewPrice =  itemView.findViewById(R.id.textViewForPrice);
            textViewDate =  itemView.findViewById(R.id.textViewForDate);
            textViewTitle =  itemView.findViewById(R.id.textViewTitle);

            textViewBarberName =itemView.findViewById(R.id.textViewNameForCard);
            textViewBarberLocation=itemView.findViewById(R.id.textViewLocation);
            textViewBarberPhone=itemView.findViewById(R.id.textViewPhone);


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
        TextView textViewBarberName=viewHolder.textViewBarberName;
        TextView textViewBarberLocation = viewHolder.textViewBarberLocation;
        TextView textViewBarberPhone = viewHolder.textViewBarberPhone;

        TextView textViewDate = viewHolder.textViewDate;
        CardView cardView = viewHolder.cardView;
        textViewBarberName.setText(dataSet.get(listPosition).getName());
        textViewBarberLocation.setText( " address: "+ dataSet.get(listPosition).getAddress());
        textViewBarberPhone.setText(" phone :"  +dataSet.get(listPosition).getPhone());
        textViewName.setText(dataSet.get(listPosition).getTitle());
        textViewPrice.setText("Price: "+ dataSet.get(listPosition).getPrice()+"");







    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}


