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

// Class of Solar rooftop calculator option
public class SolarRooftop extends AppCompatActivity {

    TextView panelCount ;
    TextView system ;
    TextView dailyUnits ;
    TextView rooftopArea ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_rooftop);

        // Initialize the elements
        Button submit = findViewById(R.id.submit);

        EditText units = findViewById(R.id.units);

        panelCount = findViewById(R.id.solarCount);
        system = findViewById(R.id.systemOut);
        dailyUnits = findViewById(R.id.dailyUnit);
        rooftopArea = findViewById(R.id.rooftopArea);

        // Initialize the spinner
        List<String> consumptionTypes = Arrays.asList("Monthly", "Yearly");
        Spinner consumptionType = findViewById(R.id.memberType);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, consumptionTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consumptionType.setAdapter(adapter);

        if(savedInstanceState!=null){   //recover the variables
            panelCount.setText(savedInstanceState.getString("panelCount"));
            system.setText(savedInstanceState.getString("system"));
            dailyUnits.setText(savedInstanceState.getString("dailyUnits"));
            rooftopArea.setText(savedInstanceState.getString("rooftopArea"));
        }

        submit.setOnClickListener(view -> {

            // Validate the input
            if (units.getText().toString().equals("0") || units.getText().toString().equals("0.0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please check the number of units.", Toast.LENGTH_LONG);
                toast.show();
                panelCount.setText("Error!");
                system.setText("Error!");
                dailyUnits.setText("Error!");
                rooftopArea.setText("Error!");

            } else if (notANumInRange(units.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "Inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                panelCount.setText("Error!");
                system.setText("Error!");
                dailyUnits.setText("Error!");
                rooftopArea.setText("Error!");
            } else {

                double unitCount = Double.parseDouble(units.getText().toString());
                String typeOfTheMember = consumptionType.getSelectedItem().toString();

                double dailyConsumption;

                switch (typeOfTheMember) {
                    case "Monthly":
                        dailyConsumption = Math.round((unitCount / 30) * 100.0) / 100.0;
                        break;
                    case "Yearly":
                        dailyConsumption = Math.round((unitCount / 365) * 100.0) / 100.0;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + typeOfTheMember);
                }

                double rooftopCapacity = Math.round((dailyConsumption / 4.5) * 10.0) /10.0;
                int panelNo = (int) Math.round(rooftopCapacity * 3);

                double areaRequired = Math.round((rooftopCapacity * 95) * 100.0) / 100.0;

                panelCount.setText(panelNo + " Panels");
                system.setText(rooftopCapacity + " Kw");
                dailyUnits.setText(dailyConsumption + " units/day");
                rooftopArea.setText(areaRequired + " sq.ft");
            }
        });

        // Initialize the '+', '-' buttons
        Button qMinus = findViewById(R.id.quantityMinus);
        Button qPlus = findViewById(R.id.quantityPlus);

        // Set the '+', '-' buttons onClickListeners
        qMinus.setOnClickListener(view -> decrementNum(units));
        qPlus.setOnClickListener(view -> incrementNum(units));

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
     * @param textView - the textView to be incremented
     */
    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Decrement the number in the EditText
     * @param textView - the textView to be decremented
     */
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        if(Double.parseDouble(textView.getText().toString())>0) {
            double newNum = Double.parseDouble(textView.getText().toString()) - 1;
            textView.setText(Double.toString(newNum));
        }
    }

    /**
     * Save the values to SharedPreferences
     * @param outState - the bundle to be saved
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("panelCount",panelCount.getText().toString());
        outState.putString("system",system.getText().toString());
        outState.putString("dailyUnits",dailyUnits.getText().toString());
        outState.putString("rooftopArea",rooftopArea.getText().toString());
    }

    /**
     * Restore the values from SharedPreferences
     * @param savedInstanceState - the bundle to be restored
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("panelCount");
        savedInstanceState.getString("system");
        savedInstanceState.getString("dailyUnits");
        savedInstanceState.getString("rooftopArea");
    }
}