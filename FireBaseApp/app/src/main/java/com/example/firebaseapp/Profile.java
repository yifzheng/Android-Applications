package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Profile extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    private final FirebaseAuth myAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        currentUser = myAuth.getCurrentUser();
        String email = currentUser.getEmail();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.logout);

        Button signoutBtn = findViewById(R.id.sign_out_btn);
        signoutBtn.setOnClickListener(view -> {
            myAuth.signOut();
            startActivity(new Intent(Profile.this, LoginActivity.class));
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                return true;
            case R.id.home:
                startActivity(new Intent(Profile.this, MainActivity.class));
                return true;
            case R.id.addNote:
                startActivity(new Intent(Profile.this, CreateNote.class));
        }
        return false;
    }
}