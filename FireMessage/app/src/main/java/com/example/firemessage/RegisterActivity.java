package com.example.firemessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firemessage.databinding.ActivityRegisterBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference userRef = db.collection("User");

    private FirebaseAuth myAuth = FirebaseAuth.getInstance();

    private ActivityRegisterBinding activityRegisterBinding;

    private TextInputEditText etRegFName, etRegLName, etRegEmail, etRegPassword;
    private TextView loginHere;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View v = activityRegisterBinding.getRoot();
        setContentView(v);
        Objects.requireNonNull(getSupportActionBar()).hide();

        etRegFName = activityRegisterBinding.registerFirstname;
        etRegLName = activityRegisterBinding.registerLastname;
        etRegEmail = activityRegisterBinding.registerEmail;
        etRegPassword = activityRegisterBinding.registerPassword;
        loginHere = activityRegisterBinding.loginHere;
        registerBtn = activityRegisterBinding.registerButton;

        registerBtn.setOnClickListener(view -> {
            createUser();
        });

       /* loginHere.setOnClickListener(view -> {
            startActivities(new Intent(RegisterActivity.this, LoginActivity.class));
        });*/
    }

    public void createUser() {
        String firstName = etRegFName.getText().toString();
        String lastName = etRegLName.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            etRegFName.setError("First Name cannot be empty");
            etRegFName.requestFocus();
        }
        if (TextUtils.isEmpty(lastName)) {
            etRegLName.setError("Last Name cannot be empty");
            etRegLName.requestFocus();
        }
        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }
        else {
            Toast.makeText(this, "Create User", Toast.LENGTH_SHORT).show();
        }
    }
}