package com.example.barbershophit.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barbershophit.Adapter.CustomAdapterService;
import com.example.barbershophit.Barber;
import com.example.barbershophit.R;
import com.example.barbershophit.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarberListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarberListFragment extends Fragment implements CustomAdapterService.OnItemClickListener {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReferenceBarber;
    List<Service> dataSet;
    List<Barber> barberList;
    private FirebaseAuth mAuth;
    static Barber barber;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    public BarberListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BarberListFragment newInstance() {
        BarberListFragment fragment = new BarberListFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("services");
        databaseReferenceBarber=firebaseDatabase.getReference().child("barber");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent i = getActivity().getIntent();
        barber = (Barber) i.getSerializableExtra("BarberData");

        dataSet= new ArrayList<>();
        barberList=new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_barber_list, container, false);

        // loadData(view,user);
        loadDataBarber(view);
            System.out.println("hereee");
        return view;
    }

    private void loadDataBarber(View view) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSet.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Service service = dataSnapshot.getValue(Service.class);
                    System.out.println(service.toString());


                    if(service.getStatus().equals("Not") && (service.getBarberId().equals(mAuth.getCurrentUser().getUid()))){
                        dataSet.add(service);

                    }

                    initRecycleView(view,dataSet);


                }


            }

            private void initRecycleView(View view, List<Service> dataSet) {
                RecyclerView recyclerView = view.findViewById(R.id.recyclerView4);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                mAuth = FirebaseAuth.getInstance();

                CustomAdapterService adapter = new CustomAdapterService(dataSet,BarberListFragment.this);
                recyclerView.setAdapter(adapter);

            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(Service item) {

    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

}