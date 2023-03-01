package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class NoteDescription extends AppCompatActivity {

    TextView title, description;
    private static final String KEY_TITLE = "title", KEY_DESCRIPTION = "description";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_description);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        Bundle extras = getIntent().getExtras();
        String displayTitle = extras.getString(KEY_TITLE);
        String displayDesc = extras.getString(KEY_DESCRIPTION);
        title = findViewById(R.id.title_textView);
        description = findViewById(R.id.description_textView);

        title.setText(displayTitle);
        description.setText(displayDesc);
        Button editNote = findViewById(R.id.UPDATE_BUTTON);
        editNote.setOnClickListener(view -> {
            Intent intent = new Intent(NoteDescription.this, UpdateNote.class);
            intent.putExtra("TITLE", displayTitle);
            intent.putExtra("DESCRIPTION", displayDesc);
            startActivity(intent);
        });
    }
}