package com.kun3awnan.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kun3awnan.app.R;

public class housing_form extends AppCompatActivity {

    private EditText house_capacity, house_description;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.housing_form_activity);


        //finding the views
        house_capacity = (EditText) findViewById(R.id.house_capacity);
        house_description = (EditText) findViewById(R.id.house_desc);
        submit = (Button) findViewById(R.id.housing_Form_Btn);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something
            }
        });

    }
}
