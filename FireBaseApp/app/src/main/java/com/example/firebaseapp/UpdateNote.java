package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateNote extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth myAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser;
    private final CollectionReference notebookRef = db.collection("Notebook"); // reference to the Notebook collection
    EditText updateTitle, updateDescription;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        currentUser = myAuth.getCurrentUser();
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("TITLE");
        String description = extras.getString("DESCRIPTION");

        updateTitle = findViewById(R.id.EDIT_TEXT_UPDATE_TITLE);
        updateDescription = findViewById(R.id.EDIT_TEXT_UPDATE_DESCRIPTION);

        updateDescription.setText(description);
        updateTitle.setText(title);

        Button updateNote = findViewById(R.id.UPDATE_NOTE_BTN);
        updateNote.setOnClickListener(view -> {
            updateNote();
        });
    }

    public void updateNote()
    {
        String title = updateTitle.getText().toString();
        String description = updateDescription.getText().toString();

    }
}