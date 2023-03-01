package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class NoteDescription extends AppCompatActivity {

    TextView title, description;
    private static final String KEY_TITLE = "title", KEY_DESCRIPTION = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_description);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        Bundle extras = getIntent().getExtras();
        title = findViewById(R.id.title_textView);
        description = findViewById(R.id.description_textView);

        title.setText(extras.getString(KEY_TITLE));
        description.setText(extras.getString(KEY_DESCRIPTION));
    }
}