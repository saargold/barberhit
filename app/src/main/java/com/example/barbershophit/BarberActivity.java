package com.example.barbershophit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class BarberActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btnAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent i = getIntent();
        Barber userData = (Barber)i.getSerializableExtra("baraberData");

        btnAddService=findViewById(R.id.btnAddService);


        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(userData);


            }



            private void addService(Barber userData, Service s1) {
//                databaseReference=firebaseDatabase.getReference("services").child(s1.getuId());
//                System.out.println(s1.getuId()+"saasar");
//                HashMap<String,Object> hashMap = new HashMap<>();
//                hashMap.put("serviceId",s1.getuId());
//                hashMap.put("barberId",userData.getId());
//                hashMap.put("name", userData.getFirstName());
//                hashMap.put("phone", userData.getPhone());
//                hashMap.put("address", userData.getAddress());
//                hashMap.put("title",s1.getTitle());
//                hashMap.put("price",s1.getPrice());
//                hashMap.put("time","time");
//                hashMap.put("status","Not");

//                databaseReference=firebaseDatabase.getReference("barber").child(mAuth.getUid()).child("services");
//                databaseReference.child(s1.getuId()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(BarberActivity.this,"publish work",Toast.LENGTH_LONG).show();
//
//                    }
//                });

               // Service s2 = new Service(s1.getuId(),userData.getFirstName(),userData.getPhone(),userData.getAddress(),s1.getTitle(),s1.getPrice(),"Not");

                Service s2 = new Service(s1.getuId(),s1.getTitle(),s1.getPrice(),"Not",mAuth.getCurrentUser().getUid(),"null");
                databaseReference=firebaseDatabase.getReference("services").child(s2.getuId());

                databaseReference.setValue(s2).addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(BarberActivity.this,"publish",Toast.LENGTH_LONG).show();

                        databaseReference=firebaseDatabase.getReference("barber").child(mAuth.getUid());
                        databaseReference.child("servicesList").child(s2.getuId()).setValue(s2);



                    }
                });






            }

//            private void addService(Barber userData, Service s1) {
//                HashMap<String,Object> hashMap = new HashMap<>();
//                s1.setId(String.valueOf(View.generateViewId()));
//                hashMap.put("serviceId",s1.getId());
//                hashMap.put("barberId",userData.getId());
//                hashMap.put("name", userData.getFirstName());
//                hashMap.put("phone", userData.getPhone());
//                hashMap.put("address", userData.getAddress());
//                hashMap.put("title",s1.getTitle());
//                hashMap.put("price",s1.getPrice());
//                hashMap.put("time","time");
//                hashMap.put("status","Not");
//                databaseReference=firebaseDatabase.getReference("services");
//                databaseReference.push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(BarberActivity.this,"publish",Toast.LENGTH_LONG).show();
//
//
//
//                    }
//                });
//
//            }

            private void showCustomDialog(Barber userData) {
                final Dialog dialog = new Dialog(BarberActivity.this);
                //We have added a title in the custom layout. So let's disable the default title.
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
                dialog.setCancelable(true);
                //Mention the name of the layout of your custom dialog.
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
                        String id = (String.valueOf(View.generateViewId()));
                        System.out.println(mAuth.getCurrentUser().getUid()+"uid");
                        String testi=mAuth.getCurrentUser().getUid();
                        Service s1 = new Service(id,name,age ,"Not",testi, "null");

                        addService(userData,s1);

                        dialog.dismiss();
                    }


                });

                dialog.show();
            }
        });



    }


}