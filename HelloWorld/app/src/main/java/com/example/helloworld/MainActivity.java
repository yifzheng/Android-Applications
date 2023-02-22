package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // onCreate() => when the application first starts, what is inside is what is run first
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // by setting content views, we are accessing the activity_main file adn establishing a relationship with this java file

    }

   /* // onClick method for button
    public void onRegistrationBtnClick(View view) {
        // access the text view created in the xml
        TextView firstName = findViewById(R.id.textFirstName); // 'R' = Resources; accessing text view
        TextView lastName = findViewById(R.id.textLastName); // 'R' = Resources; accessing text view
        TextView email = findViewById(R.id.textEmail); // 'R' = Resources; accessing text view
        EditText editFirstName = findViewById(R.id.editFirstName);
        EditText editLastName = findViewById(R.id.editLastName);
        EditText editEmail = findViewById(R.id.editEmail);
        firstName.setText(firstName.getText() + ": " + editFirstName.getText().toString()); // changed text of text view // get text and transform into string
        lastName.setText(lastName.getText() + ": " + editLastName.getText().toString()); // changed text of text view // get text and transform into string
        email.setText(email.getText() + ": " + editEmail.getText().toString()); // changed text of text view // get text and transform into string
    }*/
}