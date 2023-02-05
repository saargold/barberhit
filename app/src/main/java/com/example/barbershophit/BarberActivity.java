package com.example.barbershophit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
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
        System.out.println(userData.getEmail());
        System.out.println(userData.getLastName());
        System.out.println(userData.getFirstName());
        System.out.println(userData.getLastName());
        System.out.println(userData.getEmail());
        System.out.println(userData.getAddress());
        System.out.println(userData.getImg());
        btnAddService=findViewById(R.id.btnAddService);
        Time t1 = new Time(20,12,12);


        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(userData);


            }

            private void addService(Barber userData, Service s1) {
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("id",s1.getId());
                hashMap.put("name", userData.getFirstName());
                hashMap.put("phone", userData.getPhone());
                hashMap.put("address", userData.getAddress());
                hashMap.put("title",s1.getTitle());
                hashMap.put("price",s1.getPrice());
                hashMap.put("time","time");
                hashMap.put("place",s1.isPlace());
                databaseReference=firebaseDatabase.getReference("services");

                databaseReference.push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {

                    //                databaseReference.child(mAuth.getUid()).push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(BarberActivity.this,"publish",Toast.LENGTH_LONG).show();

                    }
                });

            }

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



                        Service s1 = new Service(mAuth.getUid(),name,age ,false);


                        addService(userData,s1);

                        dialog.dismiss();
                    }


                });

                dialog.show();
            }
        });



    }


}