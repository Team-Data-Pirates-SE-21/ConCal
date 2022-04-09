package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Flooring extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flooring);

        EditText length = findViewById(R.id.len);
        EditText width = findViewById(R.id.units);

        EditText tLength = findViewById(R.id.tLength);
        EditText tWidth = findViewById(R.id.tWidth);

        Button submit = findViewById(R.id.submit);

        TextView result1 = findViewById(R.id.cementOut);
        TextView result2 = findViewById(R.id.sandOut);
        TextView result3 = findViewById(R.id.solarCount);

        submit.setOnClickListener(v -> {

            if (length.getText().toString().equals("0.0") || width.getText().toString().equals("0.0") || tLength.getText().toString().equals("0.0") || tWidth.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                result1.setText("Error!");
                result2.setText("Error!");
                result3.setText("Error!");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(width.getText().toString()) || notANumInRange(tLength.getText().toString()) || notANumInRange(tWidth.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                result1.setText("Error!");
                result2.setText("Error!");
                result3.setText("Error!");
            } else {

                String tempLength = length.getText().toString();
                String tempWidth = width.getText().toString();
                Double floorLength = Double.parseDouble(tempLength);
                Double floorWidth = Double.parseDouble(tempWidth);

                String temp3 = tLength.getText().toString();
                String temp4 = tWidth.getText().toString();
                Double tileLength = Double.parseDouble(temp3);
                Double tileWidth = Double.parseDouble(temp4);

                double tileArea = tileLength * tileWidth;
                double floorArea = floorLength * floorWidth;

                int numTiles = (int) Math.ceil(floorArea / tileArea);
                result1.setText(String.valueOf(numTiles));

                double volumeWithBedding = floorArea * 0.07;

                int cement = (int) Math.ceil((volumeWithBedding / 7) / 0.035);
                result2.setText(cement + " bags");

                Double sand = Math.round((((volumeWithBedding * 6) / 7) * 1550) * 100) / 100.0;
                result3.setText(sand + " kg");
            }
        });

        Button lMinus = findViewById(R.id.lengthMinus);
        Button lPlus = findViewById(R.id.lengthPlus);
        Button wMinus = findViewById(R.id.quantityMinus);
        Button wPlus = findViewById(R.id.quantityPlus);

        lMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementNum(length);
            }
        });

        lPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementNum(length);
            }
        });

        wMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementNum(width);
            }
        });

        wPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementNum(width);
            }
        });

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    // validation
    private boolean notANumInRange(String strNum){
        if (strNum == null) {
            return true;//validate num is not null
        }
        try {
            double d = Double.parseDouble(strNum);//validate string is a num
            if(d<0){
                return true;//validate num cant be minus
            }
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1;
        textView.setText(Double.toString(newNum));
    }
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())-1;
        textView.setText(Double.toString(newNum));
    }
}