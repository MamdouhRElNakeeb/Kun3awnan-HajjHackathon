package com.kun3awnan.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kun3awnan.app.R;

public class Home extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    public void navigateScreen(View v){

        switch (v.getId()){

            case R.id.getLostLL:
                startActivity(new Intent(getBaseContext(), GetLost.class));
                break;
            case R.id.health_activity:
                startActivity(new Intent (Home.this, health_activity.class));
                break;
            case R.id.transport_activity:
                startActivity(new Intent (Home.this, health_form.class));
                break;
        }

    }

}
