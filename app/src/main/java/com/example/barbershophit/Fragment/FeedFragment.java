package com.example.barbershophit.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barbershophit.Barber;
import com.example.barbershophit.Adapter.CustomAdapter;
import com.example.barbershophit.R;
import com.example.barbershophit.Service;
import com.example.barbershophit.User;
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
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment implements CustomAdapter.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReferenceBarber;
    List<Service> dataSet;
    List<Barber> barberList;
    private FirebaseAuth mAuth;
    static User user;
    public FeedFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(User userData) {
        FeedFragment fragment = new FeedFragment();
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
        user = (User) i.getSerializableExtra("userData");

        View view =inflater.inflate(R.layout.fragment_feed, container, false);
        dataSet= new ArrayList<>();
        barberList=new ArrayList<>();
       // loadData(view,user);
        loadDataBarber(view,user);
        return  view;
    }

    private void loadDataBarber(View view, User user) {
        databaseReferenceBarber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                barberList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Barber barber = dataSnapshot.getValue(Barber.class);

                    barberList.add(barber);
                    initRecycleView2(view,barberList);


                }


            }

            private void initRecycleView2(View view, List<Barber> barberList) {
                RecyclerView recyclerView = view.findViewById(R.id.recyclerView1);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                mAuth = FirebaseAuth.getInstance();

                CustomAdapter adapter = new CustomAdapter(barberList,FeedFragment.this);
                recyclerView.setAdapter(adapter);

            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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


                }


            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

//    @Override
//    public void onItemClick(Service dataSet) {
//
//        String childServiceIdString = String.valueOf(dataSet.getuId());
//
//        databaseReference=firebaseDatabase.getReference("services").child(childServiceIdString);
//        databaseReference.child("status").setValue("YES");
//
//        databaseReference=firebaseDatabase.getReference("users").child(mAuth.getUid());
//        databaseReference.child("servicesList").child(childServiceIdString).setValue(dataSet);
//
//        // update userid to service
//        databaseReference=firebaseDatabase.getReference("users").child(mAuth.getUid());
//        databaseReference.child("servicesList").child(childServiceIdString).child("userId").setValue(mAuth.getUid());
//        dataSet.setUserId(mAuth.getCurrentUser().getUid());
//
//        databaseReference=firebaseDatabase.getReference("services").child(childServiceIdString);
//        databaseReference.child("userId").setValue(mAuth.getCurrentUser().getUid());
//
//
//        databaseReference=firebaseDatabase.getReference("users").child(mAuth.getUid());
//        databaseReference.child("servicesList").child(childServiceIdString).child("status").setValue("YES");
//
//        updateBarber(dataSet);
//        user.addSer(dataSet);
//
//        System.out.println(user.getServiceList()+"serre");
//
//
//
//
//
//
//
//
//    }

    private void updateBarber(Service dataSet) {
        String testId =dataSet.getuId();
        databaseReference=firebaseDatabase.getReference("barber").child(dataSet.getBarberId()).child("servicesList").child(testId);
        databaseReference.child("status").setValue("YES");
        databaseReference=firebaseDatabase.getReference("barber").child(dataSet.getBarberId()).child("servicesList").child(testId);
        databaseReference.child("userId").setValue(dataSet.getUserId());
    }


    @Override
    public void onItemClick(Barber item) {
        Fragment fragment = new ServiceFragment();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putString("YourKey", item.getId());
        fragment.setArguments(args);

        fragmentTransaction.add(R.id.fragmentContainerView, fragment);
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


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