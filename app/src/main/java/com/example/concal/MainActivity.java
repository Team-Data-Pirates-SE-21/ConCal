package com.example.concal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the cardViews
        CardView card1 = findViewById(R.id.view1);
        CardView card2 = findViewById(R.id.view2);
        CardView card3 = findViewById(R.id.view3);
        CardView card4 = findViewById(R.id.view4);
        CardView card5 = findViewById(R.id.view5);
        CardView card6 = findViewById(R.id.view6);
        CardView card7 = findViewById(R.id.view7);
        CardView card8 = findViewById(R.id.view8);
        CardView card9 = findViewById(R.id.view9);
        CardView card10 = findViewById(R.id.view10);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()) {
            case R.id.view1: i = new Intent(this,ConcreteStrength.class);startActivity(i); break;
            case R.id.view2: i = new Intent(this,BrickCalculator.class);startActivity(i); break;
            case R.id.view3: i = new Intent(this,AirConditioner.class);startActivity(i); break;
            case R.id.view4: i = new Intent(this,WoodFrame.class);startActivity(i); break;
            case R.id.view5: i = new Intent(this,Flooring.class);startActivity(i); break;
            case R.id.view6: i = new Intent(this,StairCase.class);startActivity(i); break;
            case R.id.view7: i = new Intent(this,SteelQuantity.class);startActivity(i); break;
//            case R.id.view8: i = new Intent(this,AntiTermite.class);startActivity(i); break;
            case R.id.view9: i = new Intent(this,SolarRooftop.class);startActivity(i); break;
            case R.id.view10: i = new Intent(this,WaterTank.class);startActivity(i); break;
            default: break;

        }
    }
}