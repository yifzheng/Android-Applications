package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int num1, num2, result;
    private TextView mathText;
    private Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnClear, btnNegate, btnMod, btnDiv, btnMulti, btnAdd, btnSub, btnEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        /* Initialize buttons*/
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnClear = (Button) findViewById(R.id.clearBtn);
        btnNegate=(Button) findViewById(R.id.negateBtn);
        btnMod = (Button) findViewById(R.id.modularBtn);
        btnDiv = (Button) findViewById(R.id.divideBtn);
        btnMulti = (Button) findViewById(R.id.multiplyBtn);
        btnAdd = (Button) findViewById(R.id.additionBtn);
        btnSub = (Button) findViewById(R.id.subtractionBtn);
        btnEqual = (Button) findViewById(R.id.equalsBtn);
    }
}