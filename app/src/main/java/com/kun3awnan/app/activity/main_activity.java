package com.kun3awnan.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.kun3awnan.app.R;

public class main_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initNavigationDrawer();
    }

    public void navigateScreen(View v){

        switch (v.getId()){

            case R.id.getLostLL:
                startActivity(new Intent(getBaseContext(), GetLost.class));
                break;
            case R.id.health_activity:
                startActivity(new Intent(getBaseContext(),health_activity.class));
            case R.id.food_activity:
                startActivity(new Intent(getBaseContext(),health_form.class));

        }

    }


    private void initNavigationDrawer() {

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){

                    case R.id.offerService:
                        startActivity(new Intent(main_activity.this, health_form.class));
                        break;


                }
                return true;
            }
        });

    }
}
