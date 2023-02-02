package com.example.barbershophit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarberRegister extends AppCompatActivity {
     Button register,chooseImg;
     EditText email, firstName, lastName, password,phone,address;
     ImageView IVPreviewImage;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    // the activity result code
     int SELECT_PICTURE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_barber_register);
        Barber barber;
        register = findViewById(R.id.buttonRegisterBarber);
        email = findViewById(R.id.editBarberEmail);
        password = findViewById(R.id.editBarberPassword);
        firstName = findViewById(R.id.editBarberFirstName);
        lastName = findViewById(R.id.editBarberLastName);
        phone =findViewById(R.id.editPhoneBarber);
        address=findViewById(R.id.editBarberLocationAdress);
        chooseImg = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable res = getResources().getDrawable(R.drawable.splash);
                IVPreviewImage.setImageDrawable(res);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fEmail = email.getText().toString().trim();
                String fPassword = password.getText().toString().trim();
                String fFirstname = firstName.getText().toString().trim();
                String fLastName = lastName.getText().toString().trim();
                String fPhone = phone.getText().toString().trim();
                String fAddress = address.getText().toString().trim();
                String fImg= IVPreviewImage.getResources().toString();
                mAuth.createUserWithEmailAndPassword(fEmail,fPassword).addOnCompleteListener(BarberRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(BarberRegister.this,"Regsiter Success",Toast.LENGTH_LONG).show();
                            writeDB(fEmail,fPassword,fFirstname,fLastName,fPhone,fAddress,fImg);
                        }
                        else{
                            Toast.makeText(BarberRegister.this,"Register Failed",Toast.LENGTH_LONG).show();

                        }
                    }


                    private void writeDB(String fEmail, String fPassword, String fFirstname, String fLastName,String phone,String address , String img) {

                        Barber barber = new Barber(fEmail,fPassword,fFirstname,fLastName,address,phone,img);
                        databaseReference=firebaseDatabase.getReference("barber").child(mAuth.getUid());
                        databaseReference.setValue(barber);
                        Intent i = new Intent(BarberRegister.this, BarberActivity.class);
                        i.putExtra("barberData", barber);
                        startActivity(i);
                    }
                });


            }
        });

    }
}