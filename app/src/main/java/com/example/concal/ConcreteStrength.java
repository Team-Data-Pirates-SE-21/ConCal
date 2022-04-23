package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Concrete strength predictor option class
public class ConcreteStrength extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concrete_strength);

        String url="https://concal-backend-dp.herokuapp.com/";  //url of the server

        // Initializing the elements of the activity
        Button btn=findViewById(R.id.btnPredict);
        TextView output=findViewById(R.id.txtAnswer);
        EditText input1=findViewById(R.id.editTextInput);
        EditText input2=findViewById(R.id.editTextInput2);
        EditText input3=findViewById(R.id.editTextInput3);
        EditText input4=findViewById(R.id.editTextInput4);
        EditText input5=findViewById(R.id.editTextInput5);
        EditText input6=findViewById(R.id.editTextInput6);
        EditText input7=findViewById(R.id.editTextInput7);
        EditText input8=findViewById(R.id.editTextInput8);

        // Initializing the '+' and '-' buttons of the activity
        Button minCement=findViewById(R.id.minCement);
        Button maxCement=findViewById(R.id.maxCement);
        Button minBlast=findViewById(R.id.minBlast);
        Button maxBlast=findViewById(R.id.maxBlast);
        Button minFly=findViewById(R.id.minFly);
        Button maxFly=findViewById(R.id.maxFly);
        Button minWater=findViewById(R.id.minWater);
        Button maxWater=findViewById(R.id.maxWater);
        Button minSuper=findViewById(R.id.minSuper);
        Button maxSuper=findViewById(R.id.maxSuper);
        Button minCoarse=findViewById(R.id.minCoarse);
        Button maxCoarse=findViewById(R.id.maxCoarse);
        Button minFine=findViewById(R.id.minFineAggregate);
        Button maxFine=findViewById(R.id.maxFineAggregate);
        Button minAge=findViewById(R.id.minAge);
        Button maxAge=findViewById(R.id.maxAge);

        TextView outputDefault=findViewById(R.id.txtAnswer2);
        String defaultDay="28"; // Default day(To predict the 28th day strength)

        LoadingDialog loadingDialog=new LoadingDialog(ConcreteStrength.this); // Initializing the loading dialog

        btn.setOnClickListener(view -> {

            // Input validation
            if (input1.getText().toString().equals("0") && input2.getText().toString().equals("0") && input3.getText().toString().equals("0") && input4.getText().toString().equals("0") && input5.getText().toString().equals("0") && input6.getText().toString().equals("0") && input7.getText().toString().equals("0") && input8.getText().toString().equals("0")) {
                Toast toast=Toast.makeText(getApplicationContext(),"Empty Field",Toast.LENGTH_LONG);
                toast.show();
                output.setText("0.0");
                outputDefault.setText("0.0");

            }else if (notANumInRange(input1.getText().toString()) || notANumInRange(input2.getText().toString()) || notANumInRange(input3.getText().toString()) || notANumInRange(input4.getText().toString()) || notANumInRange(input5.getText().toString()) || notANumInRange(input6.getText().toString()) || notANumInRange(input7.getText().toString()) || notANumInRange(input8.getText().toString())) {
                Toast toast=Toast.makeText(getApplicationContext(),"All should positive numbers",Toast.LENGTH_LONG);
                toast.show();
                output.setText("0.0");
                outputDefault.setText("0.0");

            }else if(input8.getText().toString().equals("0")||input8.getText().toString().equals("0.0")){
                Toast toast=Toast.makeText(getApplicationContext(),"Age can't be zero",Toast.LENGTH_LONG);
                toast.show();
                output.setText("0.0");
                outputDefault.setText("0.0");

            } else{
                loadingDialog.startLoadingDialog(); //starting the progress bar

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> { // Requesting the server
                    try {
                        JSONObject jsonObject = new JSONObject((response)); // Converting the response to JSON
                        String data = jsonObject.getString("output");   // Getting the output from the server
                        output.setText(data.toString() + " MPa");   // Setting the output to the text view
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> Toast.makeText(ConcreteStrength.this, error.getMessage(), Toast.LENGTH_LONG).show()) {
                    @Override
                    protected Map<String, String> getParams() { // Sending the data to the server
                        Map<String, String> params = new HashMap<>();   // Creating a hash map
                        String input = input1.getText().toString() + " " + input2.getText().toString() + " " + input3.getText().toString() + " " + input4.getText().toString() + " " + input5.getText().toString() + " " + input6.getText().toString() + " " + input7.getText().toString() + " " + input8.getText().toString(); // Getting the input from the text views
                        params.put("inputs", input);    // Putting the input in the hash map
                        return params;
                    }
                };

                // Predicting the default day strength
                StringRequest stringRequestDefault = new StringRequest(Request.Method.POST, url, response -> {  // Requesting the server
                    try {
                        JSONObject jsonObject = new JSONObject((response)); // Converting the response to JSON
                        String data = jsonObject.getString("output");   // Getting the output from the server
                        outputDefault.setText(data.toString() + " MPa");

                        loadingDialog.dismissDialog(); //dismissing the progress bar

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> Toast.makeText(ConcreteStrength.this, error.getMessage(), Toast.LENGTH_LONG).show()) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();   // Creating a hash map
                        String input = input1.getText().toString() + " " + input2.getText().toString() + " " + input3.getText().toString() + " " + input4.getText().toString() + " " + input5.getText().toString() + " " + input6.getText().toString() + " " + input7.getText().toString() + " " + defaultDay;  // Getting the input from the text views
                        params.put("inputs", input);    // Putting the inputs in the hash map
                        return params;
                    }
                };
                //
                RequestQueue queue = Volley.newRequestQueue(ConcreteStrength.this); // Creating a request queue
                queue.add(stringRequest);   // Adding the request to the queue
                queue.add(stringRequestDefault);
            }
        });

        // Increment text field value
        maxCement.setOnClickListener(view -> incrementNum(input1));
        maxBlast.setOnClickListener(view -> incrementNum(input2));
        maxFly.setOnClickListener(view -> incrementNum(input3));
        maxWater.setOnClickListener(view -> incrementNum(input4));
        maxSuper.setOnClickListener(view -> incrementNum(input5));
        maxCoarse.setOnClickListener(view -> incrementNum(input6));
        maxFine.setOnClickListener(view -> incrementNum(input7));
        maxAge.setOnClickListener(view -> incrementNum(input8));

        //decrement text field value
        minCement.setOnClickListener(view -> decrementNum(input1));
        minBlast.setOnClickListener(view -> decrementNum(input2));
        minFly.setOnClickListener(view -> decrementNum(input3));
        minWater.setOnClickListener(view -> decrementNum(input4));
        minSuper.setOnClickListener(view -> decrementNum(input5));
        minCoarse.setOnClickListener(view -> decrementNum(input6));
        minFine.setOnClickListener(view -> decrementNum(input7));
        minAge.setOnClickListener(view -> decrementNum(input8));

        //backButton
        ImageButton back=findViewById(R.id.imageButton);
        back.setOnClickListener(view -> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }

    /**
     *
     * @param strNum
     * @return
     */
    // Checking the user input in range
    private boolean notANumInRange(String strNum){
        if (strNum == null) {   //validate num is not null
            return true;
        }
        try {
            double d = Double.parseDouble(strNum);  //validate string is a num
            if(d<0){
                return true;    //validate num can't be a minus
            }
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param textView
     */
    @SuppressLint("SetTextI18n")
    private void incrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())+1;
        textView.setText(Double.toString(newNum));
    }

    /**
     *
     * @param textView
     */
    @SuppressLint("SetTextI18n")
    private void decrementNum(TextView textView){
        double newNum=Double.parseDouble(textView.getText().toString())-1;
        textView.setText(Double.toString(newNum));
    }
}