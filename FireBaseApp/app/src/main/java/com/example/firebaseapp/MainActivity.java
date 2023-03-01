package com.example.firebaseapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    private static final String TAG = "MainActivity";

    private static final String KEY_TITLE = "title", KEY_DESCRIPTION = "description";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference notebookRef = db.collection("Notebook"); // reference to the Notebook collection
    private TextView loadContentView;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private ArrayList<Note> noteList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide(); // hides the bar that tells app name

        Button addNoteButton = (Button) findViewById(R.id.add_note_btn);

        recyclerView = findViewById(R.id.note_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(this, noteList, this);
        recyclerView.setAdapter(noteAdapter);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            // go to new activity
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicking Add Note Button", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, CreateNote.class));
            }
        });

        // slide to delete item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Note note = noteList.get(position);

                // delete form list and database
                notebookRef.document(note.getId()).delete();
                noteList.remove(position);
                noteAdapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // this is used to automatically update loadContentView content once some data in the database gets updated
        notebookRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.d(TAG, error.toString());
                } else {
                    noteList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        Note note = documentSnapshot.toObject(Note.class); // convert retrieved data into a note object
                        note.setId(documentSnapshot.getId());
                        noteList.add(note); // add the note object into the array list
                    }
                    noteAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, NoteDescription.class);

        intent.putExtra(KEY_TITLE, noteList.get(position).getTitle());
        intent.putExtra(KEY_DESCRIPTION, noteList.get(position).getDescription());

        startActivity(intent);
    }
}