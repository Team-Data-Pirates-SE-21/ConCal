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

// Class of the stair case option
public class StairCase extends AppCompatActivity {

    TextView out ;
    TextView cementOut;
    TextView sandOut;
    TextView volumeOut;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stair_case);

        // Initialize the elements
        EditText riser = findViewById(R.id.ri);
        EditText tread = findViewById(R.id.tr);
        EditText stairwidth = findViewById(R.id.wi);
        EditText stairheight = findViewById(R.id.he);
        EditText slabthick = findViewById(R.id.th);

        out = findViewById(R.id.out);
        cementOut = findViewById(R.id.out2);
        sandOut = findViewById(R.id.out3);
        volumeOut = findViewById(R.id.out4);

        Button submit = findViewById(R.id.submit);

        // Initialize the spinner
        List<String> gradeList = Arrays.asList("M20", "M15", "M10", "M7.5");
        Spinner gradeSpinner = findViewById(R.id.gradeConcrete);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, gradeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(adapter);

        submit.setOnClickListener(v -> {

            // Validate the input
            if (riser.getText().toString().equals("0") && tread.getText().toString().equals("0") && stairwidth.getText().toString().equals("0") && stairheight.getText().toString().equals("0") && slabthick.getText().toString().equals("0")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                toast.show();
                out.setText("Error!");
                cementOut.setText("Error!");
                sandOut.setText("Error!");
                volumeOut.setText("Error!");

            } else if (notANumInRange(riser.getText().toString()) || notANumInRange(tread.getText().toString()) || notANumInRange(stairwidth.getText().toString()) || notANumInRange(stairheight.getText().toString()) || notANumInRange(slabthick.getText().toString())) {
                Toast toast = Toast.makeText(getApplicationContext(), "All inputs should be positive", Toast.LENGTH_LONG);
                toast.show();
                out.setText("Error!");
                cementOut.setText("Error!");
                sandOut.setText("Error!");
                volumeOut.setText("Error!");

            } else {

                String tempRiser = riser.getText().toString();
                String tempTread = tread.getText().toString();
                String tempStairwidth = stairwidth.getText().toString();
                String tempStairheight = stairheight.getText().toString();
                String tempSlabthick = slabthick.getText().toString();
                double riseR = Double.parseDouble(tempRiser);
                double treaD = Double.parseDouble(tempTread);
                double stairwidtH = Double.parseDouble(tempStairwidth);
                double stairheighT = Double.parseDouble(tempStairheight);
                double slabthicK = Double.parseDouble(tempSlabthick);

                double p = 2;
                double numberofRiser =(stairheighT / riseR);
                double voltotalstep = (0.5 * riseR * treaD * stairwidtH) * (numberofRiser);
                double lengthofWaistslab = (numberofRiser / treaD);
                double slantHeighteq = Math.pow(lengthofWaistslab,p) + Math.pow(10.00,p);
                double slantHeight = Math.sqrt(slantHeighteq);
                double volumeofWaistslab = stairwidtH * slabthicK * slantHeight;
                double totalVolumeofstair = voltotalstep + volumeofWaistslab;
                double dryVolume = totalVolumeofstair * 1.524;

                volumeOut.setText(String.valueOf(totalVolumeofstair));

                //Amount of cement counter

                double cementVolume;
                double sandVolume;
                double aggregateVolume;
                double sandinTon;
                double aggregateinTon;
                double cementBags;
                String gradeRatio = gradeSpinner.getSelectedItem().toString();
                switch (gradeRatio) {
                    case "M20":
                        cementVolume = (dryVolume * 1) / 5.5;
                        cementBags = cementVolume / 0.035;
                        sandVolume = (dryVolume * 1.5) / 5.5;
                        sandinTon = (sandVolume * 1550) /1000;
                        aggregateVolume = (dryVolume * 3) / 5.5;
                        aggregateinTon = (aggregateVolume) * 1350 / 1000;
                        cementOut.setText(cementBags + " Bags");
                        sandOut.setText(sandinTon + " Tons");
                        out.setText(aggregateinTon + " Tons");
                        break;
                    case "M15":
                        cementVolume = (dryVolume * 1) / 7;
                        cementBags = cementVolume / 0.035;
                        sandVolume = (dryVolume * 1.5) / 7;
                        sandinTon = (sandVolume * 1550) /1000;
                        aggregateVolume = (dryVolume * 3) / 7;
                        aggregateinTon = (aggregateVolume) * 1350 / 1000;
                        cementOut.setText(cementBags + " Bags");
                        sandOut.setText(sandinTon + " Tons");
                        out.setText(aggregateinTon + " Tons");
                        break;
                    case "M10":
                        cementVolume = (dryVolume * 1) / 10;
                        cementBags = cementVolume / 0.035;
                        sandVolume = (dryVolume * 3) / 10;
                        sandinTon = (sandVolume * 1550) /1000;
                        aggregateVolume = (dryVolume * 6) / 10;
                        aggregateinTon = (aggregateVolume) * 1350 / 1000;
                        cementOut.setText(cementBags + " Bags");
                        sandOut.setText(sandinTon + " Tons");
                        out.setText(aggregateinTon + " Tons");
                        break;
                    case "M7.5":
                        cementVolume = (dryVolume * 1) / 13;
                        cementBags = cementVolume / 0.035;
                        sandVolume = (dryVolume * 4) / 13;
                        sandinTon = (sandVolume * 1550) /1000;
                        aggregateVolume = (dryVolume * 8) / 13;
                        aggregateinTon = (aggregateVolume) * 1350 / 1000;
                        cementOut.setText(cementBags + " Bags");
                        sandOut.setText(sandinTon + " Tons");
                        out.setText(aggregateinTon + " Tons");
                        break;

                }
            }
        });

        // Initialize the '+', '-' buttons
        Button rMinus = findViewById(R.id.riserMinus);
        Button rPlus = findViewById(R.id.riserPlus);
        Button tMinus = findViewById(R.id.treadMinus);
        Button tPlus = findViewById(R.id.treadPlus);
        Button wMinus = findViewById(R.id.swidthMinus);
        Button wPlus = findViewById(R.id.swidthPlus);
        Button hMinus = findViewById(R.id.sheightMinus);
        Button hPlus = findViewById(R.id.sheightPlus);
        Button sMinus = findViewById(R.id.sthickMinus);
        Button sPlus = findViewById(R.id.sthickPlus);

        if(savedInstanceState!=null){   //recover the variables
            out.setText(savedInstanceState.getString("out"));
            cementOut.setText(savedInstanceState.getString("cementOut"));
            sandOut.setText(savedInstanceState.getString("sandOut"));
            volumeOut.setText(savedInstanceState.getString("volumeOut"));
        }

        //Setting plus, minus buttons.
        rMinus.setOnClickListener(view -> decrementNum(riser));
        rPlus.setOnClickListener(view -> incrementNum(riser));

        tMinus.setOnClickListener(view -> decrementNum(tread));
        tPlus.setOnClickListener(view -> incrementNum(tread));

        wMinus.setOnClickListener(view -> decrementNum(stairwidth));
        wPlus.setOnClickListener(view -> incrementNum(stairwidth));

        hMinus.setOnClickListener(view -> decrementNum(stairheight));
        hPlus.setOnClickListener(view -> incrementNum(stairheight));

        sMinus.setOnClickListener(view -> decrementNum(slabthick));
        sPlus.setOnClickListener(view -> incrementNum(slabthick));

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
     * Increment the number
     * @param textView - the textView to be incremented
     */
    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1.0;
        textView.setText(Double.toString(newNum));
    }

    /**
     * Decrement the number
     * @param textView - the textView to be decremented
     */
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())-1.0;
        textView.setText(Double.toString(newNum));
    }

    /**
     * save the state of the activity
     * @param outState - the bundle to save the variables
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("out",out.getText().toString());
        outState.putString("cementOut",cementOut.getText().toString());
        outState.putString("sandOut",sandOut.getText().toString());
        outState.putString("volumeOut",volumeOut.getText().toString());
    }

    /**
     * Restore the state of the activity
     * @param savedInstanceState - the bundle to restore the variables
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("out");
        savedInstanceState.getString("volumeResult");
        savedInstanceState.getString("sandOut");
        savedInstanceState.getString("volumeOut");
    }
}