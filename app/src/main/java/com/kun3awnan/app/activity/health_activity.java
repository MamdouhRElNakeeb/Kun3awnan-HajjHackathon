package com.kun3awnan.app.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.kun3awnan.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

import com.kun3awnan.app.R;


public class health_activity extends AppCompatActivity {

    //View Elements Declaration
    private LinearLayout bonesDoctor;
    private LinearLayout heartDoctor;
    private LinearLayout Dentist;
    private LinearLayout publicHealth;
    private FirebaseFirestore db;
    private DocumentReference reqRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();
        reqRef = db.collection("requests").document();
        setContentView(R.layout.health_activity);
        bonesDoctor = findViewById(R.id.bones_doctor);
        heartDoctor = findViewById(R.id.heart_doctor);
        Dentist = findViewById(R.id.dentist);
        publicHealth = findViewById(R.id.public_health);




    }
    public void navigateScreen(View v) {

        switch (v.getId()) {

            case R.id.bones_doctor:
                saveReq("Bones");
                break;
            case R.id.heart_doctor:
                saveReq("Heart");

        }

    }

    public void saveReq(String cat) {

        Map<String, Object> request = new HashMap<>();
        request.put("reqCat", "Medical Service");
        request.put("reqType", cat);

        reqRef.set(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(health_activity.this, "Request Added", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(health_activity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d("Error!", e.toString());
                    }
                });


    }

}
