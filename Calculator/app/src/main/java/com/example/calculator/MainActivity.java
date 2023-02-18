package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private boolean gotOperator, gotResult; // for num2
    private String mathString;
    private TextView mathText;
    private Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnClear, btnNegate, btnMod, btnDiv, btnMulti, btnAdd, btnSub, btnEqual, btnPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();
        /* Initialize buttons*/
        btnZero = (Button) findViewById(R.id.btnZero);
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
        btnNegate = (Button) findViewById(R.id.negateBtn);
        btnMod = (Button) findViewById(R.id.modularBtn);
        btnDiv = (Button) findViewById(R.id.divideBtn);
        btnMulti = (Button) findViewById(R.id.multiplyBtn);
        btnAdd = (Button) findViewById(R.id.additionBtn);
        btnSub = (Button) findViewById(R.id.subtractionBtn);
        btnEqual = (Button) findViewById(R.id.equalsBtn);
        btnPeriod = (Button) findViewById(R.id.btnPeriod);
        // initialize textview
        mathText = (TextView) findViewById(R.id.calcText);
        gotOperator = false;

        // IF NUMBER IS CLICKED APPEND NUMBER TO TEXT VIEW WHILE LENGTH OF TEXT < 9
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (mathString.length() < 9) {
                    if (!mathString.equals("0")) {
                        mathString += "0";
                        mathText.setText(mathString);
                    }
                }
            }
        });
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("1");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("1");
                        } else {
                            mathString += "1";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("2");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("2");
                        } else {
                            mathString += "2";
                            mathText.setText(mathString);
                        }
                    }
                }
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("3");
                    gotResult = false;
                    gotOperator = false; // TextView is cleared
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("3");
                        } else {
                            mathString += "3";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("4");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("4");
                        } else {
                            mathString += "4";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("5");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("5");
                        } else {
                            mathString += "5";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("6");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("6");
                        } else {
                            mathString += "6";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("7");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("7");
                        } else {
                            mathString += "7";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("8");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("8");
                        } else {
                            mathString += "8";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("9");
                    gotOperator = false; // TextView is cleared
                    gotResult = false;
                } else {
                    if (mathString.length() < 9) {
                        if (mathString.equals("0")) {
                            mathText.setText("9");
                        } else {
                            mathString += "9";
                            mathText.setText(mathString);
                        }
                    }
                }

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = 0;
                num2 = 0;
                result = 0;
                mathText.setText("0");
            }
        });
        btnPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                if (gotOperator || gotResult) {
                    mathText.setText("0.");
                    gotOperator = false;
                    gotResult = false;
                } else {
                    if (!mathString.contains(".")) {
                        mathString += ".";
                        mathText.setText(mathString);
                    }
                }

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(mathText.getText().toString());
                operator = '+';
                gotOperator = true;
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(mathText.getText().toString());
                operator = '-';
                gotOperator = true;
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(mathText.getText().toString());
                operator = '*';
                gotOperator = true;
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(mathText.getText().toString());
                operator = '/';
                gotOperator = true;
            }
        });
        // IF A MATH FUNCTION IS CLICKED,UPDATE NUM1
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(mathText.getText().toString());
                operator = '%';
                gotOperator = true;
            }
        });
        btnNegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathString = mathText.getText().toString();
                double mathNum = Double.parseDouble(mathString);
                if (isInteger(mathNum)) {
                    mathText.setText(String.valueOf((int) mathNum * -1));
                } else {
                    mathText.setText(String.valueOf((double) mathNum * -1));
                }
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2 = Double.parseDouble(mathText.getText().toString());
                gotResult = true;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    case '%':
                        result = num1 % num2;
                        break;
                    default:
                        break;
                }
                if (isInteger(result)) {
                    mathText.setText(String.valueOf((int) result));
                } else {
                    mathText.setText(String.valueOf((double) result));
                }
                num1 = (double) result;
            }
        });

    }

    public boolean isInteger(Double num) {
        return Math.ceil(num) == Math.floor(num);
    }
}