package com.example.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etLogEmail, etLogPassword;
    TextView registerHere;
    Button login;
    private FirebaseAuth myAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        etLogEmail = findViewById(R.id.login_email);
        etLogPassword = findViewById(R.id.login_password);
        login = findViewById(R.id.btn_login);
        registerHere = findViewById(R.id.register_here);
        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        login.setOnClickListener(view -> {
            loginUser();
        });
    }

    public void loginUser() {
        String email = etLogEmail.getText().toString();
        String password = etLogPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etLogEmail.setError("Email cannot be empty");
            etLogEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etLogPassword.setError("Password cannot be empty");
            etLogPassword.requestFocus();
        } else {
            myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = myAuth.getCurrentUser();
                        if (user.isEmailVerified()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}