package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class SteelQuantity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steel_quantity);

        Button submit = findViewById(R.id.submit);

        TextView outputs = findViewById(R.id.out3);

        List<String> memberTypes = Arrays.asList("Footing", "Beam", "Column", "Slab", "StairCase", "Lintle/Coping", "Retaining Wall");
        Spinner memberType = findViewById(R.id.memberType);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, memberTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberType.setAdapter(adapter);

        EditText quantity = findViewById(R.id.width2);

        submit.setOnClickListener(view -> {

            if (quantity.getText().toString().equals("0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty field", Toast.LENGTH_LONG);
                toast.show();
                outputs.setText("Error!");

            } else if (notANumInRange(quantity.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "Inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                outputs.setText("Error!");
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

        Button qMinus = findViewById(R.id.quantityMinus);
        Button qPlus = findViewById(R.id.quantityPlus);

        qMinus.setOnClickListener(view -> decrementNum(quantity));
        qPlus.setOnClickListener(view -> incrementNum(quantity));

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