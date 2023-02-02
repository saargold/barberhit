package com.example.barbershophit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

public class Feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Intent i = getIntent();
        User userData = (User)i.getSerializableExtra("userData");
        System.out.println(userData.getEmail());
        System.out.println(userData.getLastName());
        System.out.println(userData.getFirstName());
        System.out.println(userData.getLastName());
        Fragment fragment =FragmentMain.newInstance(userData.getEmail(),userData.getFirstName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment, "fragmain");
        fragmentTransaction.commit();




    }
}