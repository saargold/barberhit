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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  {
    public interface OnItemClickListener {
        void onItemClick(Barber item);
    }

    private  List<Service> dataSet;
    private  List<Barber> barberList;

    private  OnItemClickListener  clickListener;
//
//    public CustomAdapter(List<Service> dataSet ,OnItemClickListener clickListener )  {
//
//        this.dataSet = dataSet;
//        this.clickListener = clickListener;
//
//    }

    public CustomAdapter(List<Barber> barberList ,OnItemClickListener clickListener )  {

        this.barberList = barberList;
        this.clickListener = clickListener;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        CardView cardView;
        TextView textViewTitle;
        TextView textViewPrice;
//        TextView textViewDate;
        TextView textViewBarberName;
        TextView textViewBarberLocation;
        TextView textViewBarberPhone;


        public MyViewHolder (View itemView )
        {
            super(itemView);

            cardView =  itemView.findViewById(R.id.card_view);
            textViewPrice =  itemView.findViewById(R.id.textViewForPrice);
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

//        TextView textViewDate = viewHolder.textViewDate;
        CardView cardView = viewHolder.cardView;
        textViewBarberName.setText(barberList.get(listPosition).getFirstName()+""+barberList.get(listPosition).getLastName());
        textViewBarberLocation.setText( " address: "+ barberList.get(listPosition).getAddress());
        textViewBarberPhone.setText(" phone :"  +barberList.get(listPosition).getPhone());
        int pos = listPosition;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(barberList.get(pos).toString() +"tostr");
                clickListener.onItemClick(barberList.get(pos));

            }
        });








    }
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int listPosition) {
//
//        TextView textViewName = viewHolder.textViewTitle;
//        TextView textViewPrice = viewHolder.textViewPrice;
//        TextView textViewBarberName=viewHolder.textViewBarberName;
//        TextView textViewBarberLocation = viewHolder.textViewBarberLocation;
//        TextView textViewBarberPhone = viewHolder.textViewBarberPhone;
//
//        TextView textViewDate = viewHolder.textViewDate;
//        CardView cardView = viewHolder.cardView;
//        textViewBarberName.setText(dataSet.get(listPosition).getName());
//        textViewBarberLocation.setText( " address: "+ dataSet.get(listPosition).getAddress());
//        textViewBarberPhone.setText(" phone :"  +dataSet.get(listPosition).getPhone());
//        textViewName.setText(dataSet.get(listPosition).getTitle());
//        textViewPrice.setText("Price: "+ dataSet.get(listPosition).getPrice()+"");
//        int pos = listPosition;
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println(dataSet.get(pos).toString() +"tostr");
//                clickListener.onItemClick(dataSet.get(pos));
//
//            }
//        });
//
//
//
//
//
//
//
//
//    }

    @Override
    public int getItemCount() {
        return barberList.size();
    }




}


