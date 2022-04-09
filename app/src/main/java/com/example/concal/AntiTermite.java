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

public class AntiTermite extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_termite);

        EditText length = findViewById(R.id.wLength);
        EditText width = findViewById(R.id.wWidth);

        Button calculate = findViewById(R.id.cal);

        TextView result = findViewById(R.id.capacity);

        calculate.setOnClickListener(v -> {

            if (length.getText().toString().equals("0.0") || width.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please check the inputs.", Toast.LENGTH_LONG);
                toast.show();
                result.setText("Error!");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(width.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                result.setText("Error!");
            } else {

                String tempL = length.getText().toString();
                double l = Double.parseDouble(tempL);
                String tempD = width.getText().toString();
                double w = Double.parseDouble(tempD);


                double area = l * w;
                Double quantity = area * 30;

                result.setText(quantity + "ml");
            }
        });

        Button lMinus = findViewById(R.id.lMinus);
        Button lPlus = findViewById(R.id.lPlus);
        Button wMinus = findViewById(R.id.wMinus);
        Button wPlus = findViewById(R.id.wPlus);

        lMinus.setOnClickListener(v -> decrementNum(length));
        lPlus.setOnClickListener(v -> incrementNum(length));

        wMinus.setOnClickListener(v -> decrementNum(width));
        wPlus.setOnClickListener(v -> incrementNum(width));

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