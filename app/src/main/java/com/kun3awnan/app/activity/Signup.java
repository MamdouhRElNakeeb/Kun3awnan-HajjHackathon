package com.kun3awnan.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;


import com.google.firebase.iid.FirebaseInstanceId;
import com.kun3awnan.app.R;

public class Signup extends AppCompatActivity {
    private EditText name, email, password, phone,nationalID;
    private Button signup_btn;
    private FirebaseFirestore db;
    private DocumentReference userRef;
    ProgressBar progressBar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        // find the views
        name = findViewById(R.id.nameET);
        email = findViewById(R.id.emailET);
        password = findViewById(R.id.passET);
        phone = findViewById(R.id.mobileET);
        nationalID = findViewById(R.id.statementNoET);
        signup_btn = findViewById(R.id.RegisterBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

//        updateDB();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pudh into Firebase Authentication Users Table
                String email_field = email.getText().toString();
                String password_field = password.getText().toString();

                if (TextUtils.isEmpty(email_field)) {
                    Toast.makeText(getApplicationContext(), "Email is required!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password_field)) {
                    Toast.makeText(getApplicationContext(), "Password is required!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email_field, password_field)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //Toast.makeText(Activity_Signup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Signup.this, "Registration Successful",Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    updateDB();
                                }
                                else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getApplicationContext(), "You are already registered!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);

                                    } else {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            }
                        });

            }
        });
    }

    private void updateDB(){

        userRef = db.collection("users").document();


        // push into Firestore Database
        String name_field = name.getText().toString();
        String phone_field = phone.getText().toString();
        String nationalID_field = nationalID.getText().toString();
        String deviceToken = FirebaseInstanceId.getInstance().getToken();
        String userID = auth.getCurrentUser().getUid();

        Log.d("userid", userID);

        Log.d("devToken", deviceToken);

        Map<String, Object> note = new HashMap<>();
        note.put("name", name_field);
        //note.put("email", email_field);
        //note.put("password", password_field);
        note.put("phone", phone_field);
        note.put("nationalID", nationalID_field);
        note.put("deviceToken", deviceToken);

        db.collection("users").document(userID)
                .set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Signup.this, "User Added", Toast.LENGTH_SHORT).show();
                        sendVerificationEmail();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d("ERROR!", e.toString());
                    }
                });

    }
    // send email for verification
    private void sendVerificationEmail()
    {
        final FirebaseUser userr = FirebaseAuth.getInstance().getCurrentUser();
        userr.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent
                            Toast.makeText(Signup.this,"A Verification email was sent to " + userr.getEmail(), Toast.LENGTH_SHORT).show();
                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(Signup.this, Login.class));
                            finish();
                        }
                        else
                        {//restart this activit
                            Toast.makeText(getApplicationContext(), "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}

