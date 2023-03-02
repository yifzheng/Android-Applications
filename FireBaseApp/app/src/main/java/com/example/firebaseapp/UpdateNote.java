package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateNote extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {
    private static final String KEY_TITLE = "title", KEY_DESCRIPTION = "description", KEY_NOTEID = "noteID";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth myAuth = FirebaseAuth.getInstance();

    private final CollectionReference notebookRef = db.collection("Notebook"); // reference to the Notebook collection
    EditText updateTitle, updateDescription;
    private BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        Log.d("UPDATE_NOTE", extras.toString());
        String title = extras.getString(KEY_TITLE);
        String description = extras.getString(KEY_DESCRIPTION);
        String noteID = extras.getString(KEY_NOTEID);

        updateTitle = findViewById(R.id.EDIT_TEXT_UPDATE_TITLE);
        updateDescription = findViewById(R.id.EDIT_TEXT_UPDATE_DESCRIPTION);

        updateTitle.setText(title);
        updateDescription.setText(description);

        Button updateNote = findViewById(R.id.UPDATE_NOTE_BTN);
        Button cancelUpdate = findViewById(R.id.CANCEL_UPDATE_NOTE_BTN);
        updateNote.setOnClickListener(view -> {
            updateNote(noteID);
        });
        cancelUpdate.setOnClickListener(view -> {
            finish();
        });
    }

    public void updateNote(String noteID) {
        String title = updateTitle.getText().toString();
        String description = updateDescription.getText().toString();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, title);
        note.put(KEY_DESCRIPTION, description);

        notebookRef.document(noteID).update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                finish(); // move back to the UpdateNote activity
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateNote.this, "Error Updating Note: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.d("UPDATE_NOTE", e.getMessage().toString());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                myAuth.signOut();
                startActivity(new Intent(UpdateNote.this, LoginActivity.class));
                return true;
            case R.id.home:
                startActivity(new Intent(UpdateNote.this, MainActivity.class));
                return true;
            case R.id.addNote:
                startActivity(new Intent(UpdateNote.this, CreateNote.class));
        }
        return false;
    }
}