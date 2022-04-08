package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WaterTank extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tank);

        EditText length = findViewById(R.id.wLength);
        EditText width = findViewById(R.id.wWidth);
        EditText depth = findViewById(R.id.depth);

        Button calculate = findViewById(R.id.cal);

        TextView result = findViewById(R.id.capacity);
        TextView volumeResult = findViewById(R.id.volumeOut2);

        calculate.setOnClickListener(v -> {

            if (length.getText().toString().equals("0.0") || width.getText().toString().equals("0.0") || depth.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                result.setText("Error!");
                volumeResult.setText("Error!");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(width.getText().toString()) || notANumInRange(depth.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                result.setText("Error!");
                volumeResult.setText("Error!");
            } else {

                String tempL = length.getText().toString();
                double l = Double.parseDouble(tempL);
                String tempW = width.getText().toString();
                double w = Double.parseDouble(tempW);
                String tempD = depth.getText().toString();
                double d = Double.parseDouble(tempD);


                double volume = Math.round(l * w * d);
                Double quantity = volume * 1000;

                result.setText(quantity + " lt");
                volumeResult.setText(volume + " m^3");
            }
        });

        Button lMinus = findViewById(R.id.lMinus);
        Button lPlus = findViewById(R.id.lPlus);
        Button wMinus = findViewById(R.id.wMinus);
        Button wPlus = findViewById(R.id.wPlus);
        Button dMinus = findViewById(R.id.dMinus);
        Button dPlus = findViewById(R.id.dPlus);

        lMinus.setOnClickListener(v -> decrementNum(length));
        lPlus.setOnClickListener(v -> incrementNum(length));

        wMinus.setOnClickListener(v -> decrementNum(width));
        wPlus.setOnClickListener(v -> incrementNum(width));

        dMinus.setOnClickListener(v -> decrementNum(depth));
        dPlus.setOnClickListener(v -> incrementNum(depth));
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