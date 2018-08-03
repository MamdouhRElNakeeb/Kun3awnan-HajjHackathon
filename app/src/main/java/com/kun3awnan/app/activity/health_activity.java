package com.kun3awnan.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kun3awnan.app.R;


public class health_activity extends AppCompatActivity {

    //View Elements Declaration
    private LinearLayout bonesDoctor;
    private LinearLayout heartDoctor;
    private LinearLayout Dentist;
    private LinearLayout publicHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_activity);
        bonesDoctor = findViewById(R.id.bones_doctor);
        heartDoctor = findViewById(R.id.heart_doctor);
        Dentist = findViewById(R.id.dentist);
        publicHealth = findViewById(R.id.public_health);


    }


}
