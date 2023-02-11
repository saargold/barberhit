package com.example.barbershophit.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershophit.Adapter.CustomAdapterOrders;
import com.example.barbershophit.Adapter.CustomAdapterOrdersBarber;
import com.example.barbershophit.Barber;
import com.example.barbershophit.BarberActivity;
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
 * Use the {@link BarberListFragmentOld#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarberListFragmentOld extends Fragment  implements CustomAdapterOrdersBarber.OnItemClickListener {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2,databaseReference3,userRef,userRefList,mDatabase,userRefList1;
    private FirebaseAuth mAuth;
    List<Service> dataSet;
    List<String> userIdList;
    static Barber barber;
    // TODO: Rename parameter arguments, choose names that match


    // TODO: Rename and change types of parameters

    public BarberListFragmentOld() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BarberListFragmentOld newInstance( ) {
        BarberListFragmentOld fragment = new BarberListFragmentOld();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        databaseReference=firebaseDatabase.getReference().child("users").child("servicesList");
        System.out.println(mAuth.getCurrentUser().getUid()+"iidd");
        databaseReference3=firebaseDatabase.getReference().child("users").child(mAuth.getCurrentUser().getUid());
        userRef = mDatabase.child("barber").child(mAuth.getCurrentUser().getUid()).getRef();
        userRefList = mDatabase.child("barber").child(mAuth.getCurrentUser().getUid()).child("servicesList").getRef();


        dataSet= new ArrayList<>();
        userIdList= new ArrayList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent i = getActivity().getIntent();
        barber = (Barber) i.getSerializableExtra("baraberData");
        dataSet= new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_barber_list_old, container, false);

        loadDataListServices(view);
        System.out.println("");
        return view;
    }

    private void loadDataListServices(View view) {
        userRef.child("servicesList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot==null)return;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    userIdList.add(snapshot.getKey());
                }
                for (int i=0;i<userIdList.size();i++){
                    System.out.println(userIdList.get(i)+"the key");
                }
                loadData(view);


            }

            @Override
            public void onCancelled(DatabaseError   databaseError) {
                // Error
            }
        });

    }

    private void loadData(View view) {
        dataSet.clear();
        for (int i=0;i<userIdList.size();i++){
            System.out.println(i+"index");
            System.out.println(userIdList.get(i)+"thek");

            userRefList.child(userIdList.get(i)).addListenerForSingleValueEvent(

            new ValueEventListener () {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Service service = dataSnapshot.getValue(Service.class);
                            if(service.getStatus().equals("YES")){
                                dataSet.add(service);

                            }

                            initRecycleView(view,dataSet);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
        }
    }

    private void initRecycleView(View view, List<Service> dataSet) {

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView5);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        CustomAdapterOrdersBarber adapter = new CustomAdapterOrdersBarber(dataSet,BarberListFragmentOld.this);
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onItemClick(Service item) {

}


    }
