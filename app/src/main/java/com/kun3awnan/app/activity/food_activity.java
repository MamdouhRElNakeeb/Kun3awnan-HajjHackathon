package com.kun3awnan.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;


import com.kun3awnan.app.R;

public class food_activity extends AppCompatActivity {


    private TextView toolbar_text;
    private ListView listView;
    private FloatingActionButton floatingActionButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);


        toolbar_text = (TextView) findViewById(R.id.toolbarText);
        listView = (ListView) findViewById(R.id.food_lv);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(food_activity.this, food_form.class));
            }
        });
    }
}
