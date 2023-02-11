package com.example.barbershophit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.barbershophit.Fragment.FeedFragment;
import com.example.barbershophit.Fragment.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feed extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReferenceBarber;
    private FirebaseAuth mAuth;
    User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("services");
        databaseReferenceBarber = firebaseDatabase.getReference("barber");
        FragmentManager fragmentManager = getSupportFragmentManager();

        Button feed = findViewById(R.id.btnFeedHome);
        Button profile = findViewById(R.id.btnFeedProfile);

        Intent i = getIntent();
         userData = (User)i.getSerializableExtra("userData");
         i.putExtra("userData",userData);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                userData = (User)i.getSerializableExtra("userData");
                i.putExtra("userData",userData);
                Fragment fragment = new FeedFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment,"FeedFragment")
                        .addToBackStack("FeedFragment")

                        .commit();


            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                userData = (User)i.getSerializableExtra("userData");
                Fragment fragment = new ProfileFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment,"ProfileFragment")
                        .addToBackStack("ProfileFragment")
                        .commit();
            }
        });










    }

//    private void loadData() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dataSet.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Service service = dataSnapshot.getValue(Service.class);
//                    if(!service.isPlace()){
//                    dataSet.add(service);
//                    }
//                    initRecycleView(dataSet);
//
//
//                }
//
//
//            }
//
//            private void initRecycleView(List<Service> dataSet) {
//                RecyclerView recyclerView = findViewById(R.id.recyclerView);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                recyclerView.setLayoutManager(layoutManager);
//                mAuth = FirebaseAuth.getInstance();
//
//                CustomAdapter adapter = new CustomAdapter(dataSet);
//                recyclerView.setAdapter(adapter);
//
//
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }



}
