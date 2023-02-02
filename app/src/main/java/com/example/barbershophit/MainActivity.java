package com.example.barbershophit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
Button connectBtn,registerBtn,registerBarber,connectBtnBarber;
EditText email,password;
private FirebaseAuth mAuth;
FirebaseDatabase firebaseDatabase;
CheckBox check;
Boolean isBarber =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        connectBtn = findViewById(R.id.btnConnect);
        connectBtnBarber=findViewById(R.id.btnConnectBarber);
        registerBarber=findViewById(R.id.registerBarber);
        registerBtn = findViewById(R.id.btnGoToRegisterUser);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        check = findViewById(R.id.checkBarber);
        DatabaseReference databaseReference,databaseReferenceBarber;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        databaseReferenceBarber = firebaseDatabase.getReference("barber");


        registerBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(MainActivity.this, BarberRegister.class);
                startActivity(in1);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(MainActivity.this, UserRegister.class);
                startActivity(in1);
            }
        });

        connectBtnBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cEmail = email.getText().toString();
                String cPassword = password.getText().toString();
                mAuth.signInWithEmailAndPassword(cEmail,cPassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userKey = user.getUid();
                                    databaseReferenceBarber.child(userKey).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String fName = String.valueOf(snapshot.child("firstName").getValue());
                                            String lName = String.valueOf(snapshot.child("lastName").getValue());
                                            String email = String.valueOf(snapshot.child("email").getValue());
                                            String password = String.valueOf(snapshot.child("password").getValue());
                                            String address = String.valueOf(snapshot.child("address").getValue());
                                            String phone = String.valueOf(snapshot.child("phone").getValue());
                                            String img = String.valueOf(snapshot.child("img").getValue());

                                            Barber barber=new Barber(email,password,fName,lName,address,phone,img);
                                            Intent i = new Intent(MainActivity.this, BarberActivity.class);
                                            i.putExtra("baraberData", barber);
                                            startActivity(i);

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();

                                }

                            }

                        });

            }
        });
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(email.getText().toString());
                System.out.println(password.getText().toString());
                String cEmail = email.getText().toString();
                String cPassword = password.getText().toString();
                System.out.println("is" + isBarber);
                    mAuth.signInWithEmailAndPassword(cEmail,cPassword)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){

                                            Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            String userKey = user.getUid();
                                            databaseReference.child(userKey).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String fName = String.valueOf(snapshot.child("firstName").getValue());
                                                    String lName = String.valueOf(snapshot.child("lastName").getValue());
                                                    String email = String.valueOf(snapshot.child("email").getValue());
                                                    String password = String.valueOf(snapshot.child("password").getValue());

                                                    User user1=new User(email,password,fName,lName);
                                                    Intent i = new Intent(MainActivity.this, Feed.class);
                                                    i.putExtra("userData", user1);
                                                    startActivity(i);

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }
                                        else{
                                            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();

                                        }






                                }

                            });


            }



        });
    }


    public void checkbox_clicked(View view) {
        if(check.isChecked()){
            connectBtnBarber.setVisibility(View.VISIBLE);
            connectBtn.setVisibility(View.GONE);

        }
        else {
            connectBtnBarber.setVisibility(View.GONE);
            connectBtn.setVisibility(View.VISIBLE);

        }
    }
}


