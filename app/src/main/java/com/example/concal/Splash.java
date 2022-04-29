package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// Splash screen of the application
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // After delay of 3 seconds, go to the home screen
        new Handler().postDelayed(() -> {
            Intent i=new Intent(Splash.this,MainActivity.class);
            startActivity(i);
            finish();
        },3000);
}}