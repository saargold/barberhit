package com.example.barbershophit;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barbershophit.Fragment.BarberListFragment;
import com.example.barbershophit.Fragment.BarberListFragmentOld;
import com.example.barbershophit.Fragment.FeedFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BarberActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button availableList,notAvailable;
    Button btnAddNew;
    Barber userData;
    String uniqueID = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent i = getIntent();
        userData = (Barber)i.getSerializableExtra("baraberData");
        FragmentManager fragmentManager = getSupportFragmentManager();
        System.out.println("barberdata"+userData.getId());
         availableList = findViewById(R.id.btnBarberMyList);
         notAvailable = findViewById(R.id.btnBarberMyListFinished);
         btnAddNew=findViewById(R.id.floatingActionButton);

         availableList.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = getIntent();
                 i.putExtra("baraberData",userData);
                 Fragment fragment = new BarberListFragment();
                 fragmentManager.beginTransaction()
                         .replace(R.id.fragmentContainerView2,fragment,"BarberListFragment")
                         .addToBackStack("BarberListFragment")
                         .commit();


             }
         });
         notAvailable.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = getIntent();
                 i.putExtra("BarberData",userData);
                 Fragment fragment = new BarberListFragmentOld();
                 fragmentManager.beginTransaction()
                         .replace(R.id.fragmentContainerView2,fragment,"BarberListFragmentOld")
                         .addToBackStack("BarberListFragmentOld")
                         .commit();
             }
         });


        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(userData);


            }



            private void addService(Barber userData, Service s1) {

                Service s2 = new Service(s1.getuId(),s1.getTitle(),s1.getPrice(),"Not",mAuth.getCurrentUser().getUid(),"null");
                databaseReference=firebaseDatabase.getReference("services").child(s2.getuId());

                databaseReference.setValue(s2).addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(BarberActivity.this,"Service id:"+s1.getuId()+"add to list",Toast.LENGTH_LONG).show();

                        databaseReference=firebaseDatabase.getReference("barber").child(mAuth.getUid());
                        databaseReference.child("servicesList").child(s2.getuId()).setValue(s2);



                    }
                });






            }



            private void showCustomDialog(Barber userData) {
                final Dialog dialog = new Dialog(BarberActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.custom_dialog);

                //Initializing the views of the dialog.
                final EditText nameEt = dialog.findViewById(R.id.name_et);
                final EditText ageEt = dialog.findViewById(R.id.age_et);


                Button submitButton = dialog.findViewById(R.id.submit_button);



                mAuth = FirebaseAuth.getInstance();




                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nameEt.getText().toString();
                        int age = Integer.parseInt(ageEt.getText().toString());
                        System.out.println(mAuth.getCurrentUser().getUid()+"uid");
                        String testi=mAuth.getCurrentUser().getUid();
                        Service s1 = new Service(uniqueID,name,age ,"Not",testi, "null");

                        addService(userData,s1);

                        dialog.dismiss();
                    }


                });

                dialog.show();
            }
        });



    }


}