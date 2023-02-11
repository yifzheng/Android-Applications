package com.example.uibasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    private TextView txtHello;
    private EditText editHello;
    private Button btnHello;
    */

    private CheckBox harryPotterCB, matrixCB, avengersCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  txtHello = findViewById(R.id.txtHello); // retrieve textview
        editHello = findViewById(R.id.editTextName); // retrieve edittext
        btnHello = findViewById(R.id.btnHello); // retrieve id for button
        btnHello.setOnClickListener(this);
        // onLongClickListener
        btnHello.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Long Press Clicked", Toast.LENGTH_LONG).show();
                return true;
            }
        });*/

        harryPotterCB = findViewById(R.id.checkboxHP);
        matrixCB = findViewById(R.id.checkboxMatrix);
        avengersCB = findViewById(R.id.checkboxAvengers);

        if (harryPotterCB.isChecked()) {
            Toast.makeText(MainActivity.this, "You have watched Harry Potter", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "You need to watch Harry Potter", Toast.LENGTH_SHORT).show();
        }
        harryPotterCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "You have watched Harry Potter", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You need to watch Harry Potter", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHello:
                txtHello.setText(new StringBuilder().append(txtHello.getText()).append(" ").append(editHello.getText().toString()).toString());
                Toast.makeText(this, "Hello Button Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }*/

    /*public void onHelloBtnClick(View view) {
        TextView txtHello = findViewById(R.id.txtWelcome);
        txtHello.setText("Hello Again");
    }*/
}