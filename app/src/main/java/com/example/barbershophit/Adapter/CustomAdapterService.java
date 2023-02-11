package com.example.barbershophit.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershophit.Barber;
import com.example.barbershophit.R;
import com.example.barbershophit.Service;

import java.util.List;

public class CustomAdapterService extends RecyclerView.Adapter<CustomAdapterService.MyViewHolder>  {
    public interface OnItemClickListener {
        void onItemClick(Service item);


    }

    private  List<Service> dataSet;
    private  OnItemClickListener  clickListener;

    public CustomAdapterService(List<Service> dataSet , OnItemClickListener clickListener )  {

        this.dataSet = dataSet;
        this.clickListener = clickListener;

    }



    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        CardView cardView;
        TextView textViewTitle;
        TextView textViewPrice;



        public MyViewHolder (View itemView )
        {
            super(itemView);

            cardView =  itemView.findViewById(R.id.card_view);
            textViewPrice =  itemView.findViewById(R.id.textViewForPrice);
            textViewTitle =  itemView.findViewById(R.id.textViewTitle);



        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext() ).inflate(R.layout.cards_orders_layout , parent ,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int listPosition) {

        TextView textViewName = viewHolder.textViewTitle;
        TextView textViewPrice = viewHolder.textViewPrice;

        CardView cardView = viewHolder.cardView;

        textViewName.setText(dataSet.get(listPosition).getTitle());
        textViewPrice.setText("Price: "+ dataSet.get(listPosition).getPrice()+"");
        int pos = listPosition;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(dataSet.get(pos).toString() +"tostr");
                clickListener.onItemClick(dataSet.get(pos));

            }
        });








    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }




}


