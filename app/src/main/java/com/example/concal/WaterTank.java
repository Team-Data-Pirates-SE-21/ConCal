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

// Class of water tank size calculator option
public class WaterTank extends AppCompatActivity {

    EditText length;
    EditText width;
    EditText depth;
    TextView result;
    TextView volumeResult;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tank);

        // Initialize the elements
        length = findViewById(R.id.wLength);
        width = findViewById(R.id.wWidth);
        depth = findViewById(R.id.depth);

        Button calculate = findViewById(R.id.cal);

        result = findViewById(R.id.capacity);
        volumeResult = findViewById(R.id.volumeOut2);

        if(savedInstanceState!=null){   //recover the variables
            result.setText(savedInstanceState.getString("result"));
            volumeResult.setText(savedInstanceState.getString("volumeResult"));
        }

        calculate.setOnClickListener(v -> {

            // Validate the input
            if (length.getText().toString().equals("0.0") || width.getText().toString().equals("0.0") || depth.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                result.setText("");
                volumeResult.setText("");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(width.getText().toString()) || notANumInRange(depth.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                result.setText("");
                volumeResult.setText("");
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
                volumeResult.setText(volume + " m\u00B3");
            }
        });

        // Initialize the '+', '-' buttons
        Button lMinus = findViewById(R.id.lMinus);
        Button lPlus = findViewById(R.id.lPlus);
        Button wMinus = findViewById(R.id.wMinus);
        Button wPlus = findViewById(R.id.wPlus);
        Button dMinus = findViewById(R.id.dMinus);
        Button dPlus = findViewById(R.id.dPlus);

        // Set the '+', '-' buttons onClickListeners
        lMinus.setOnClickListener(v -> decrementNum(length));
        lPlus.setOnClickListener(v -> incrementNum(length));

        wMinus.setOnClickListener(v -> decrementNum(width));
        wPlus.setOnClickListener(v -> incrementNum(width));

        dMinus.setOnClickListener(v -> decrementNum(depth));
        dPlus.setOnClickListener(v -> incrementNum(depth));

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }

    /**
     * Validate the input
     * @param strNum - the user input
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
     * Save the state of the app
     * @param outState - the state of the app
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result",result.getText().toString());
        outState.putString("volumeResult",volumeResult.getText().toString());
    }

    /**
     * Restore the state of the app
     * @param savedInstanceState - the state of the app
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("result");
        savedInstanceState.getString("volumeResult");
    }
}