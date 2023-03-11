package com.example.firemessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firemessage.databinding.ActivityStartBinding;

import java.util.Objects;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding activityStartBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStartBinding = ActivityStartBinding.inflate(getLayoutInflater());
        View v = activityStartBinding.getRoot();
        setContentView(v);
        Objects.requireNonNull(getSupportActionBar()).hide();

        activityStartBinding.startActivityRegisterBtn.setOnClickListener(view -> {
            startActivity(new Intent(StartActivity.this, RegisterActivity.class));
        });
    }
}