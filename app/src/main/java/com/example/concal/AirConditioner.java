package com.example.concal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AirConditioner extends AppCompatActivity {

    TextView acSizeText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioner);

        // Initialize the elements
        EditText rLength = findViewById(R.id.rLength);
        EditText rBreadth = findViewById(R.id.rBreadth);
        EditText rHeight = findViewById(R.id.rHeight);
        EditText pCount = findViewById(R.id.personCount);
        EditText maxTemp = findViewById(R.id.maxTemp);

        acSizeText = findViewById(R.id.output);

        Button submit = findViewById(R.id.calculate);

        if(savedInstanceState!=null){   //recovering the variables
            acSizeText.setText(savedInstanceState.getString("acSize"));
        }

        submit.setOnClickListener(v -> {

            if (rLength.getText().toString().equals("0.0") || rBreadth.getText().toString().equals("0.0") || rHeight.getText().toString().equals("0.0") || pCount.getText().toString().equals("0") || maxTemp.getText().toString().equals("0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                acSizeText.setText("Error!");

            } else if (notANumInRange(rLength.getText().toString()) || notANumInRange(rBreadth.getText().toString()) || notANumInRange(rHeight.getText().toString()) || notANumInRange(pCount.getText().toString()) || notANumInRange(maxTemp.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                acSizeText.setText("Error!");
            } else {
                final String tempLength = rLength.getText().toString();
                final String tempBreadth = rBreadth.getText().toString();
                final String tempHeight = rHeight.getText().toString();
                final String tempPCount = pCount.getText().toString();
                final String tempMax = maxTemp.getText().toString();

                final double length = Double.parseDouble(tempLength);
                final double breadth = Double.parseDouble(tempBreadth);
                final double height = Double.parseDouble(tempHeight);
                final double personCount = Double.parseDouble(tempPCount);
                final double maximumTemp = Double.parseDouble(tempMax);

                double noOfPerson;
                double temperature;
                double heightEquation;

                if (personCount <= 3) {
                    noOfPerson = 0.3;
                } else {
                    noOfPerson = 0.3 + (personCount - 3) * 0.07;
                }

                if (maximumTemp > 45) {
                    temperature = 0.5;
                } else if (maximumTemp > 40 && maximumTemp <= 45) {
                    temperature = 0.4;
                } else if (maximumTemp > 35 && maximumTemp <= 40) {
                    temperature = 0.3;
                } else {
                    temperature = 0.2;
                }

                if (height <= 8.0) {
                    heightEquation = 0.0;
                } else {
                    heightEquation = (height - 8.0) * 0.1;
                }

                double acSize = Math.round((((length * breadth * 20) / 12000) + noOfPerson + temperature + heightEquation) * 100.0) / 100.0;
                acSizeText.setText(acSize + " Ton");
            }
        });

        Button lMinus = findViewById(R.id.lm);
        Button lPlus = findViewById(R.id.lu);
        Button bMinus = findViewById(R.id.bm);
        Button bPlus = findViewById(R.id.bu);
        Button hMinus = findViewById(R.id.hm);
        Button hPlus = findViewById(R.id.hu);
        Button pMinus = findViewById(R.id.pm);
        Button pPlus = findViewById(R.id.pu);
        Button tMinus = findViewById(R.id.tm);
        Button tPlus = findViewById(R.id.tu);

        lMinus.setOnClickListener(view -> decrementNum(rLength));
        lPlus.setOnClickListener(view -> incrementNum(rLength));

        bMinus.setOnClickListener(view -> decrementNum(rBreadth));
        bPlus.setOnClickListener(view -> incrementNum(rBreadth));

        hMinus.setOnClickListener(view -> decrementNum(rHeight));
        hPlus.setOnClickListener(view -> incrementNum(rHeight));

        pMinus.setOnClickListener(view -> decrementPNum(pCount));
        pPlus.setOnClickListener(view -> incrementPNum(pCount));

        tMinus.setOnClickListener(view -> decrementNum(maxTemp));
        tPlus.setOnClickListener(view -> incrementNum(maxTemp));

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }

    /**
     * Vakidating the user input
     * @param strNum - user input
     * @return - true if the input is valid
     */
    private boolean notANumInRange(String strNum){
        if (strNum == null) {
            return true;    //validate num is not null
        }
        try {
            double d = Double.parseDouble(strNum);  //validate string is a num
            if(d<0){
                return true;    //validate num cant be minus
            }
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    /**
     * increment the number in the text view
     * @param textView - the text view to be incremented
     */
    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Decrement the number in the text view
     * @param textView - the text view to decrement
     */
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())-1;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Increment the number of people
     * @param textView - textView to be incremented
     */
    @SuppressLint("SetTextI18n")
    private void incrementPNum(TextView textView){
        int newNum = (int)Double.parseDouble(textView.getText().toString())+1;
        textView.setText(Integer.toString(newNum));
    }

    /**
     * Decrement the number of people
     * @param textView - the text view to decrement
     */
    @SuppressLint("SetTextI18n")
    private void decrementPNum(TextView textView){
        int newNum = (int)Double.parseDouble(textView.getText().toString())-1;
        textView.setText(Integer.toString(newNum));
    }

    /**
     * save the state of the activity
     * @param outState - bundle to save the state
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("acSize",acSizeText.getText().toString());
    }

    /**
     * restore the state of the activity
     * @param savedInstanceState - bundle to restore the state
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("acSize");
    }
}