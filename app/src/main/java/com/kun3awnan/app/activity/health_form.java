package com.kun3awnan.app.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kun3awnan.app.R;

public class health_form extends AppCompatActivity {

    private Spinner spinner;
    private String spinner_choice;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_form_activity);

        // find views
        spinner = (Spinner) findViewById(R.id.hcare_spinner);
        button = (Button) findViewById(R.id.HCare_Form_Btn);

        //  Setting the time slots of TA
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.medicalSpecializtion));
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);


        //  Retrieve the selected timeslot
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_choice = parent.getItemAtPosition(position).toString();
                //Toast.makeText(Activity_TA_Reservation.this, timeSpinner_value , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Handling the submit button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // specilaization is  stored as a text in spinner_choice
                // Do your thing

                FirebaseMessaging.getInstance().subscribeToTopic("health")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = "subscribed";
                                if (!task.isSuccessful()) {
                                    msg = "failed";
                                }
                                Log.d("statusOfTopic", msg);
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}
