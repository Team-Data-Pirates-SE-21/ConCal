package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WoodFrame extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wood_frame);

        EditText length = findViewById(R.id.wLength);
        EditText depth = findViewById(R.id.wWidth);
        EditText thickness = findViewById(R.id.fThickness);

        TextView result = findViewById(R.id.capacity);

        Button calculate = findViewById(R.id.cal);

        calculate.setOnClickListener(v -> {

            if (length.getText().toString().equals("0.0") || depth.getText().toString().equals("0.0") || thickness.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                result.setText("Error!");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(depth.getText().toString()) || notANumInRange(thickness.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                result.setText("Error!");
            } else {
                String tempL = length.getText().toString();
                double l = Double.parseDouble(tempL);
                String tempD = depth.getText().toString();
                double d = Double.parseDouble(tempD);
                String tempT = thickness.getText().toString();
                double t = Double.parseDouble(tempT);

                double volume = l * t * d;

                result.setText(volume + " m^3");
            }
        });

        Button lMinus = findViewById(R.id.lMinus);
        Button lPlus = findViewById(R.id.lPlus);
        Button dMinus = findViewById(R.id.wMinus);
        Button dPlus = findViewById(R.id.wPlus);
        Button tMinus = findViewById(R.id.tMinus);
        Button tPlus = findViewById(R.id.tPlus);

        //Setting plus, minus buttons.
        lMinus.setOnClickListener(view -> decrementNum(length));
        lPlus.setOnClickListener(view -> incrementNum(length));

        dMinus.setOnClickListener(view -> decrementNum(depth));
        dPlus.setOnClickListener(view -> incrementNum(depth));

        tMinus.setOnClickListener(view -> decrementNum(thickness));
        tPlus.setOnClickListener(view -> incrementNum(thickness));
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