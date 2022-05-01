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

// Class of AntiTermite option
public class AntiTermite extends AppCompatActivity {

    TextView result;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_termite);

        // Initialize the elements
        EditText length = findViewById(R.id.wLength);
        EditText width = findViewById(R.id.wWidth);

        Button calculate = findViewById(R.id.cal);

        result = findViewById(R.id.capacity);

        calculate.setOnClickListener(v -> {

            // Validate the input
            if (length.getText().toString().equals("0.0") || width.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please check the inputs.", Toast.LENGTH_LONG);
                toast.show();
                result.setText("");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(width.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                result.setText("");

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

        // Initialize the '+', '-' buttons
        Button lMinus = findViewById(R.id.lMinus);
        Button lPlus = findViewById(R.id.lPlus);
        Button wMinus = findViewById(R.id.wMinus);
        Button wPlus = findViewById(R.id.wPlus);

        // Set the '+', '-' buttons' onClickListener
        lMinus.setOnClickListener(v -> decrementNum(length));
        lPlus.setOnClickListener(v -> incrementNum(length));

        wMinus.setOnClickListener(v -> decrementNum(width));
        wPlus.setOnClickListener(v -> incrementNum(width));

        if(savedInstanceState!=null){   //recover the variables
            result.setText(savedInstanceState.getString("result"));
        }

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }

    /**
     * Validate the user input
     * @param strNum - the user input
     * @return - true if the input is a positive number
     */
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

    /**
     * Increment the number in the EditText
     * @param textView - textView to be incremented
     */
    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Decrement the number in the EditText
     * @param textView - textView to be decremented
     */
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        if(Double.parseDouble(textView.getText().toString())>0) {
            double newNum = Double.parseDouble(textView.getText().toString()) - 1;
            textView.setText(Double.toString(newNum));
        }
    }

    /**
     * save the state of the activity
     * @param outState - bundle to save the state
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result",result.getText().toString());
    }

    /**
     * restore the state of the activity
     * @param savedInstanceState - bundle to restore the state
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("result");
    }
}