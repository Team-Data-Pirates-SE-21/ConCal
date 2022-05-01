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

// Class of Steel Quantity option
public class SteelQuantity extends AppCompatActivity {

    TextView outputs;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steel_quantity);

        // Initialize the elements
        Button submit = findViewById(R.id.submit);

        outputs = findViewById(R.id.out3);

        EditText quantity = findViewById(R.id.width2);

        // Initialize the spinner
        List<String> memberTypes = Arrays.asList("Footing", "Beam", "Column", "Slab", "StairCase", "Lintle/Coping", "Retaining Wall");
        Spinner memberType = findViewById(R.id.memberType);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, memberTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberType.setAdapter(adapter);

        if(savedInstanceState!=null){   //recover the variables
            outputs.setText(savedInstanceState.getString("outputs"));
        }

        submit.setOnClickListener(view -> {

            // Validate the inputs
            if (quantity.getText().toString().equals("0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty field", Toast.LENGTH_LONG);
                toast.show();

            } else if (notANumInRange(quantity.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "Inputs should be positive", Toast.LENGTH_LONG);
                toast.show();

            } else {

                double concreteQuantity = Double.parseDouble(quantity.getText().toString());
                String typeOfTheMember = memberType.getSelectedItem().toString();

                double value;

                switch (typeOfTheMember) {
                    case "Footing":
                    case "Slab":
                        value = 80 * concreteQuantity;
                        break;
                    case "Beam":
                        value = 160 * concreteQuantity;
                        break;
                    case "Column":
                        value = 110 * concreteQuantity;
                        break;
                    case "StairCase":
                        value = 85 * concreteQuantity;
                        break;
                    case "Lintle/Coping":
                        value = 50 * concreteQuantity;
                        break;
                    case "Retaining Wall":
                        value = 60 * concreteQuantity;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + typeOfTheMember);
                }

                String finalResult = Double.valueOf(value).toString();
                outputs.setText(finalResult + " Kg");
            }
        });

        // Initialize the '+', '-' buttons
        Button qMinus = findViewById(R.id.quantityMinus);
        Button qPlus = findViewById(R.id.quantityPlus);

        // set the onClickListener for the '+', '-' buttons
        qMinus.setOnClickListener(view -> decrementNum(quantity));
        qPlus.setOnClickListener(view -> incrementNum(quantity));

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }

    /**
     * Validate the inputs
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
        double newNum=Double.parseDouble(textView.getText().toString())-1;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Save the inputs to the SharedPreferences
     * @param outState - the state of the activity
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("outputs",outputs.getText().toString());
    }

    /**
     * Restore the inputs from the SharedPreferences
     * @param savedInstanceState - the state of the activity
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("outputs");
    }
}