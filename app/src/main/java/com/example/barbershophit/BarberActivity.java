package com.example.barbershophit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class BarberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);

        Intent i = getIntent();
        Barber userData = (Barber)i.getSerializableExtra("baraberData");
        System.out.println(userData.getEmail());
        System.out.println(userData.getLastName());
        System.out.println(userData.getFirstName());
        System.out.println(userData.getLastName());
        System.out.println(userData.getEmail());
        System.out.println(userData.getAddress());
        System.out.println(userData.getImg());
    }
}