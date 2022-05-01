package com.example.concal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

// Class of brick calculator option
public class BrickCalculator extends AppCompatActivity {

    TextView out;
    TextView cementOut;
    TextView sandOut;
    int cementBags;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brick_calculator);

        // Initializing the elements
        EditText length = findViewById(R.id.len);
        EditText height = findViewById(R.id.units);

        EditText bLength = findViewById(R.id.tLength);
        EditText bWidth = findViewById(R.id.tWidth);
        EditText bHeight = findViewById(R.id.bHeight);

        out = findViewById(R.id.cementOut);
        cementOut = findViewById(R.id.sandOut);
        sandOut = findViewById(R.id.solarCount);

        Button submit = findViewById(R.id.submit);

        // Thickness selector
        List<String> thicknessList = Arrays.asList("10cm wall", "23cm wall");
        Spinner thicknessSpinner = findViewById(R.id.memberType);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, thicknessList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thicknessSpinner.setAdapter(adapter);

        // Ratio selector
        List<String> ratioList = Arrays.asList("C.M 1:3", "C.M 1:4", "C.M 1:5", "C.M 1:6", "C.M 1:7", "C.M 1:8", "C.M 1:9");
        Spinner ratioSpinner = findViewById(R.id.ratioSpinner);
        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, ratioList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratioSpinner.setAdapter(adapter2);

        if(savedInstanceState!=null){   //recovering the variables
            out.setText(savedInstanceState.getString("brickCount"));
            cementOut.setText(savedInstanceState.getString("cementBags"));
            sandOut.setText(savedInstanceState.getString("sand"));
        }

        submit.setOnClickListener(v -> {

            // Validating the user inputs
            if (length.getText().toString().equals("0.0") || height.getText().toString().equals("0.0") || bLength.getText().toString().equals("0.0") || bWidth.getText().toString().equals("0.0") || bHeight.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                out.setText("Error!");
                cementOut.setText("Error!");
                sandOut.setText("Error!");

            } else if (notANumInRange(length.getText().toString()) || notANumInRange(height.getText().toString()) || notANumInRange(bLength.getText().toString()) || notANumInRange(bWidth.getText().toString()) || notANumInRange(bHeight.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                out.setText("Error!");
                cementOut.setText("Error!");
                sandOut.setText("Error!");

            } else {
                // Number of bricks counter

                double wallThickness;
                String tempLength = length.getText().toString();
                String tempWidth = height.getText().toString();
                double wallLength = Double.parseDouble(tempLength);
                double wallHeight = Double.parseDouble(tempWidth);
                String wallThicknessString = thicknessSpinner.getSelectedItem().toString();
                if (wallThicknessString.equals("10cm wall")) {
                    wallThickness = 10.0/100;
                } else {
                    wallThickness = 23.0/100;
                }

                String temp3 = bLength.getText().toString();
                String temp4 = bWidth.getText().toString();
                String temp5 = bHeight.getText().toString();
                double brickLength = Double.parseDouble(temp3);
                double brickWidth = Double.parseDouble(temp4);
                double brickHeight = Double.parseDouble(temp5);

                double brickMasonry = wallLength * wallHeight * wallThickness;
                double brickVolume = (brickLength + 0.01) * (brickWidth + 0.01) * (brickHeight + 0.01);

                int noOfBricks;
                double tempBricks = ((brickMasonry / brickVolume) % 10);
                if (tempBricks == 0) {
                    noOfBricks = (int) (brickMasonry / brickVolume);
                } else {
                    noOfBricks = (int) (brickMasonry / brickVolume) + 1;
                }

                out.setText(String.valueOf(noOfBricks));

                //Amount of cement and sand counter

                double cement;
                double sand;
                double tempSandQuantity;
                double sandQuantity;
                double bricksMortar = noOfBricks * (brickLength * brickWidth * brickHeight);
                double tempMortar = brickMasonry - bricksMortar;
                double tempMortar2 = tempMortar + (tempMortar * 0.15); // For the wastage
                double mortarQuantity = tempMortar2 + (tempMortar2 * 0.25); // For the dry volume

                String wallRatio = ratioSpinner.getSelectedItem().toString();
                switch (wallRatio) {
                    case "C.M 1:3":
                        cement = mortarQuantity / 4.0;
                        sand = mortarQuantity * (3 / 4.0);
                        cementRounder(cement);
                        tempSandQuantity = Math.ceil((sand * 1500));
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                    case "C.M 1:4":
                        cement = mortarQuantity / 5.0;
                        sand = mortarQuantity * (4 / 5.0);
                        cementRounder(cement);
                        tempSandQuantity = sand * 1500;
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                    case "C.M 1:5":
                        cement = mortarQuantity / 6.0;
                        sand = mortarQuantity * (5 / 6.0);
                        cementRounder(cement);
                        tempSandQuantity = sand * 1500;
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                    case "C.M 1:6":
                        cement = mortarQuantity / 7.0;
                        sand = mortarQuantity * (6 / 7.0);
                        cementRounder(cement);
                        tempSandQuantity = sand * 1500;
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                    case "C.M 1:7":
                        cement = mortarQuantity / 8.0;
                        sand = mortarQuantity * (7 / 8.0);
                        cementRounder(cement);
                        tempSandQuantity = sand * 1500;
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                    case "C.M 1:8":
                        cement = mortarQuantity / 9.0;
                        sand = mortarQuantity * (8 / 9.0);
                        cementRounder(cement);
                        tempSandQuantity = sand * 1500;
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                    case "C.M 1:9":
                        cement = mortarQuantity / 10.0;
                        sand = mortarQuantity * (9 / 10.0);
                        cementRounder(cement);
                        tempSandQuantity = sand * 1500;
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                    default:
                        //Invalid
                        cement = mortarQuantity;
                        sand = mortarQuantity;
                        cementRounder(cement);
                        tempSandQuantity = sand * 1500;
                        sandQuantity = Math.round((tempSandQuantity) / 1000 * 100.0) / 100.0;
                        cementOut.setText(cementBags + " bags");
                        sandOut.setText(sandQuantity + " ton");
                        break;
                }
            }
        });

        // Initialize the '+', '-' buttons
        Button lMinus = findViewById(R.id.lengthMinus);
        Button lPlus = findViewById(R.id.lengthPlus);
        Button hMinus = findViewById(R.id.quantityMinus);
        Button hPlus = findViewById(R.id.quantityPlus);

        //Setting plus, minus buttons.
        lMinus.setOnClickListener(view -> decrementNum(length));
        lPlus.setOnClickListener(view -> incrementNum(length));

        hMinus.setOnClickListener(view -> decrementNum(height));
        hPlus.setOnClickListener(view -> incrementNum(height));

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }

    private void cementRounder(double num) {

        double tempCement = num / 0.035;
        if (tempCement == 0) {
            cementBags = (int) (num / 0.035);
        } else {
            cementBags = (int) (num / 0.035) + 1;
        }
    }
    /**
     * Validating the user input
     * @param strNum - the user input
     * @return - true if the input is valid
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
     * Increasing the value of the textView
     * @param textView - the textView to be incremented
     */
    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1.0;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Decreasing the value of the textView
     * @param textView -  the textView to be decremented
     */
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())-1.0;
        textView.setText(Double.toString(newNum));
    }

    /**
     * save the state of the activity
     * @param outState - bundle to save the state
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("brickCount",out.getText().toString());
        outState.putString("cementBags",cementOut.getText().toString());
        outState.putString("sand",sandOut.getText().toString());
    }

    /**
     * restore the state of the activity
     * @param savedInstanceState - bundle to restore the state
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("brickCount");
        savedInstanceState.getString("cementBags");
        savedInstanceState.getString("sand");
    }
}