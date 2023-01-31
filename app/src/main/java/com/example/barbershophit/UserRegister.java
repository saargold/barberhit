package com.example.barbershophit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private Button register;
    private EditText email, firstName, lastName, password;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        User user;
        register = findViewById(R.id.buttonRegister);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println(firstName.getText().toString());
//                System.out.println(lastName.getText().toString());
//                System.out.println(email.getText().toString());
//                System.out.println(password.getText().toString());
                String fEmail = email.getText().toString().trim();
                String fPassword = password.getText().toString().trim();
                String fFirstname = firstName.getText().toString().trim();
                String fLastName = lastName.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(fEmail,fPassword).addOnCompleteListener(UserRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserRegister.this,"Regsiter Success",Toast.LENGTH_LONG).show();
                            writeDB(fEmail,fPassword,fFirstname,fLastName);
                        }
                        else{
                            Toast.makeText(UserRegister.this,"Register Failed",Toast.LENGTH_LONG).show();

                        }
                    }

                    private void writeDB(String fEmail, String fPassword, String fFirstname, String fLastName) {
                      String newId= String.valueOf(View.generateViewId());

                        User user1 = new User(fEmail,fPassword,newId,fFirstname,fLastName);
                        databaseReference=firebaseDatabase.getReference("users").child(user1.getId());
                        databaseReference.setValue(user1);
                    }
                });


            }
        });
        }

    }
