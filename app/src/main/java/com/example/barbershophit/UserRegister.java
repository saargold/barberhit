package com.example.barbershophit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserRegister extends AppCompatActivity {

    Button register;
    EditText email, firstName, lastName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        register = findViewById(R.id.buttonRegister);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(firstName.getText().toString());
                System.out.println(lastName.getText().toString());
                System.out.println(email.getText().toString());
                System.out.println(password.getText().toString());
            }
        });

    }
}