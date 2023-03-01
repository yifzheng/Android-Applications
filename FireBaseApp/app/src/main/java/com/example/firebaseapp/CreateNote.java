package com.example.firebaseapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class CreateNote extends AppCompatActivity {
    private static final String TAG = "CREATE_NOTE_ACTIVITY";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference notebookRef = db.collection("Notebook"); // reference to the Notebook collection
    private EditText title, description;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        title = (EditText) findViewById(R.id.EDIT_TEXT_TITLE);
        description = (EditText) findViewById(R.id.EDIT_TEXT_DESCRIPTION);
        saveBtn = (Button) findViewById(R.id.SAVE_NOTE_BTN);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNoteToDataBase();
            }
        });
    }

    public void addNoteToDataBase()
    {
        String editTextTitle = title.getText().toString();
        String editTextDesc = description.getText().toString();

        Note note = new Note(editTextTitle, editTextDesc);

        notebookRef.add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                finish(); // go back to main activity
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateNote.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
            }
        });
    }
}