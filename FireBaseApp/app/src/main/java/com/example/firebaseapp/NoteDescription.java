package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class NoteDescription extends AppCompatActivity {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance(); // database reference
    private final CollectionReference notebookRef = db.collection("Notebook"); // reference to the Notebook collection
    private DocumentReference noteRef;
    TextView title, description;
    private static final String KEY_TITLE = "title", KEY_DESCRIPTION = "description", KEY_NOTEID = "noteID";

    private String displayTitle, displayDescription;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_description);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        Bundle extras = getIntent().getExtras();
        String noteID = extras.getString(KEY_NOTEID);
        noteRef = notebookRef.document(noteID);
        title = findViewById(R.id.title_textView);
        description = findViewById(R.id.description_textView);
        notebookRef.document(noteID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                {
                    Note note = documentSnapshot.toObject(Note.class);
                    displayTitle = note.getTitle();
                    displayDescription = note.getDescription();
                    title.setText(displayTitle);
                    description.setText(displayDescription);
                }
            }
        });

        Button editNote = findViewById(R.id.UPDATE_BUTTON);
        editNote.setOnClickListener(view -> {
            Intent intent = new Intent(NoteDescription.this, UpdateNote.class);
            intent.putExtra(KEY_NOTEID, noteID);
            intent.putExtra(KEY_TITLE, displayTitle);
            intent.putExtra(KEY_DESCRIPTION, displayDescription);
            startActivity(intent);
        });
        Button deleteNote = findViewById(R.id.DELETE_BUTTON);
        deleteNote.setOnClickListener(view -> {
            noteRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    finish(); // deleted so move back to main activity
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(NoteDescription.this, "Error Deleting Note: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("DISPLAY_NOTE", e.getMessage().toString());
                }
            });
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        noteRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null)
                {
                    Log.d("NOTE_DESCRIPTION", error.toString());
                }
                else
                {
                    Note note = value.toObject(Note.class);
                    title.setText(note.getTitle());
                    description.setText(note.getDescription());
                }
            }
        });
    }

}