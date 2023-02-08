package com.example.barbershophit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment  implements CustomAdapterOrders.OnItemClickListener{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2,databaseReference3,userRef,userRefList,mDatabase;
    private FirebaseAuth mAuth;
    List<Service> dataSet;
    List<String> userIdList;
    TextView title1,price1;
    static User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters


    public ProfileFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(User userData) {

        ProfileFragment fragment = new ProfileFragment();
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
        userRef = mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("servicesList").getRef();
        userRefList = mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).getRef();


        dataSet= new ArrayList<>();
        userIdList= new ArrayList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent i = getActivity().getIntent();
        user = (User) i.getSerializableExtra("userData");
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        loadDataListServices(view);





        return view;

    }

    private void loadDataListServices(View view) {
        userRefList.child("servicesList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot==null)return;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    userIdList.add(snapshot.getKey());
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

            userRef.child(userIdList.get(i)).addListenerForSingleValueEvent(
                    new ValueEventListener () {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Service user = dataSnapshot.getValue(Service.class);
                            dataSet.add(user);
                            System.out.println(dataSet.size()+"sssarr");
                            initRecycleView(view,dataSet);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Getting Post failed, log a message
                        }
                    });
        }
    }


    private void initRecycleView(View view1,List<Service> dataSet) {
        RecyclerView recyclerView = view1.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        CustomAdapterOrders adapter = new CustomAdapterOrders(dataSet,ProfileFragment.this);
        recyclerView.setAdapter(adapter);



    }


    @Override
    public void onItemClick(Service item) {

    }
}

