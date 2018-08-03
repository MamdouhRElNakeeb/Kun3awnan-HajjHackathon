package com.kun3awnan.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;


import com.kun3awnan.app.Models.food;
import com.kun3awnan.app.R;
import com.kun3awnan.app.adapter.ListViewAdapter;

import java.util.ArrayList;

public class food_activity extends AppCompatActivity {



    private TextView toolbar_text;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private ListViewAdapter adapter;
    private ArrayList<food> arrayList = new ArrayList<food>();

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

        arrayList.add(new food("Mulukhiya", "Tasty and yummy"));
        arrayList.add(new food("Kushari", "hot and spicy"));
        adapter = new ListViewAdapter(this, arrayList );
        listView.setAdapter(adapter);
    }
}
