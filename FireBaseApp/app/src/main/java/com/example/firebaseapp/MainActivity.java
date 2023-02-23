package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String KEY_TITLE = "title", KEY_DESCRIPTION = "description";

    private EditText editTextTitle, editTextDescription;
    private Button saveButton, loadButton, updateButton, deleteTitleButton, deleteDescButton, deleteNoteButton;
    private TextView loadContentView;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference notebookRef = db.collection("Notebook"); // reference to the Notebook collection
    private final DocumentReference noteRef = db.collection("Notebook").document("alpha");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        editTextTitle = (EditText) findViewById(R.id.edit_title_view);
        editTextDescription = (EditText) findViewById(R.id.edit_description_view);
        saveButton = (Button) findViewById(R.id.save_btn);
        loadButton = (Button) findViewById(R.id.load_btn);
        updateButton = (Button) findViewById(R.id.update_btn);
        deleteDescButton = (Button) findViewById(R.id.delete_desc_btn);
        deleteTitleButton = (Button) findViewById(R.id.delete_title_btn);
        deleteNoteButton = (Button) findViewById(R.id.delete_note_btn);
        loadContentView = (TextView) findViewById(R.id.textview_content_load);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();

                Note note = new Note(title, description);

                //<---For general document ID ------->
                /*notebookRef.add(note).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Successfully saved \n ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                        editTextDescription.setText(""); // clear edit view
                        editTextTitle.setText(""); // clear edit view
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });*/
                noteRef.set(note).addOnSuccessListener((OnSuccessListener) (aVoid) -> Toast.makeText(MainActivity.this, "Note Saved", Toast.LENGTH_SHORT).show()).addOnFailureListener((e) -> {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                });
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Note note = documentSnapshot.toObject(Note.class);
                            String title = note.getTitle();
                            String description = note.getDescription();
                            loadContentView.setText("Title: " + title + "\n" + "Description: " + description);
                        } else {
                            Toast.makeText(MainActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = editTextDescription.getText().toString();
                String title = editTextTitle.getText().toString();
                Map<String, Object> note = new HashMap<>();
                if (!description.equals("")) {
                    note.put(KEY_DESCRIPTION, description);
                } else if (!title.equals("")) {
                    note.put(KEY_TITLE, title);
                }
                noteRef.update(note);
            }
        });

        deleteTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteRef.update(KEY_TITLE, FieldValue.delete());
            }
        });

        deleteDescButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteRef.update(KEY_DESCRIPTION, FieldValue.delete());
            }
        });

        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
                noteRef.delete();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // this is used to automatically update loadContentView content once some data in the database gets updated
        noteRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(MainActivity.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, error.toString());
                    return;
                }
                if (value.exists()) {
                    Note note = value.toObject(Note.class);
                    String title = note.getTitle();
                    String description = note.getDescription();
                    loadContentView.setText("Title: " + title + "\n" + "Description: " + description);
                } else {
                    loadContentView.setText("");
                }
            }
        });
    }

    public void clearFields() {
        editTextTitle.setText("");
        editTextDescription.setText("");
    }
}