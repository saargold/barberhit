package com.example.barbershophit;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
 * Use the {@link ServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceFragment extends Fragment implements CustomAdapterService.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReferenceBarber;
    List<Service> dataSet;

    private FirebaseAuth mAuth;
    static User user;
    public ServiceFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ServiceFragment newInstance(User userData) {
        ServiceFragment fragment = new ServiceFragment();
        Bundle args = new Bundle();



        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
         databaseReference=firebaseDatabase.getReference().child("services");
        databaseReferenceBarber=firebaseDatabase.getReference().child("barber");
        //databaseReference=firebaseDatabase.getReference().child("barber").child("services");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent i = getActivity().getIntent();
        user = (User) i.getSerializableExtra("userData");

        View view =inflater.inflate(R.layout.fragment_services, container, false);
        dataSet= new ArrayList<>();
       loadData(view,user);
        return  view;
    }




    private void loadData(View view, User user) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSet.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Service service = dataSnapshot.getValue(Service.class);
                    System.out.println(service.toString());


                    if(service.getStatus().equals("Not")){
                        dataSet.add(service);

                    }
                    initRecycleView3(view,dataSet);


                }


            }

            private void initRecycleView3(View view1,List<Service> dataSet) {
                RecyclerView recyclerView = view1.findViewById(R.id.recyclerView3);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                mAuth = FirebaseAuth.getInstance();

                CustomAdapterService adapter = new CustomAdapterService(dataSet,ServiceFragment.this);
                recyclerView.setAdapter(adapter);



            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onItemClick(Service dataSet) {

        String childServiceIdString = String.valueOf(dataSet.getuId());

        databaseReference=firebaseDatabase.getReference("services").child(childServiceIdString);
        databaseReference.child("status").setValue("YES");

        databaseReference=firebaseDatabase.getReference("users").child(mAuth.getUid());
        databaseReference.child("servicesList").child(childServiceIdString).setValue(dataSet);

        // update userid to service
        databaseReference=firebaseDatabase.getReference("users").child(mAuth.getUid());
        databaseReference.child("servicesList").child(childServiceIdString).child("userId").setValue(mAuth.getUid());
        dataSet.setUserId(mAuth.getCurrentUser().getUid());

        databaseReference=firebaseDatabase.getReference("services").child(childServiceIdString);
        databaseReference.child("userId").setValue(mAuth.getCurrentUser().getUid());


        databaseReference=firebaseDatabase.getReference("users").child(mAuth.getUid());
        databaseReference.child("servicesList").child(childServiceIdString).child("status").setValue("YES");

        updateBarber(dataSet);
        user.addSer(dataSet);

        System.out.println(user.getServiceList()+"serre");








    }

    private void updateBarber(Service dataSet) {
        String testId =dataSet.getuId();
        databaseReference=firebaseDatabase.getReference("barber").child(dataSet.getBarberId()).child("servicesList").child(testId);
        databaseReference.child("status").setValue("YES");
        databaseReference=firebaseDatabase.getReference("barber").child(dataSet.getBarberId()).child("servicesList").child(testId);
        databaseReference.child("userId").setValue(dataSet.getUserId());
    }



}