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

// Class for the wood frame calculator option
public class WoodFrame extends AppCompatActivity {

    TextView result;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wood_frame);

        // Initialize the elements
        EditText length = findViewById(R.id.wLength);
        EditText depth = findViewById(R.id.wWidth);
        EditText thickness = findViewById(R.id.fThickness);

        result = findViewById(R.id.capacity);

        Button calculate = findViewById(R.id.cal);

        if(savedInstanceState!=null){   //recovering the variables
            result.setText(savedInstanceState.getString("output"));
        }

        calculate.setOnClickListener(v -> {

            // Validate the inputs
            if (length.getText().toString().equals("0.0") || depth.getText().toString().equals("0.0") || thickness.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                result.setText("");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(depth.getText().toString()) || notANumInRange(thickness.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                result.setText("");

            } else {
                String tempL = length.getText().toString();
                double l = Double.parseDouble(tempL);
                String tempD = depth.getText().toString();
                double d = Double.parseDouble(tempD);
                String tempT = thickness.getText().toString();
                double t = Double.parseDouble(tempT);

                double volume = Math.round((l * t * d) * 100.0) / 100.0;

                result.setText(volume + " m\u00B3");
            }
        });

        // Initialize the '+', '-' buttons
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

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }

    /**
     * Validate the user input
     * @param strNum - user input
     * @return - true if the input is a positive number
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
     * Increment the number
     * @param textView - the textView that should be incremented
     */
    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Decrement the number
     * @param textView - the textView that should be decremented
     */
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())-1;
        textView.setText(Double.toString(newNum));
    }

    /**
     * save the state of the activity
     * @param outState - bundle to save the state
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("output",result.getText().toString());
    }

    /**
     * restore the state of the activity
     * @param savedInstanceState - bundle to restore the state
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("output");
    }
}