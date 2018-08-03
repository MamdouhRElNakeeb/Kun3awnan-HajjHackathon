package com.kun3awnan.app.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kun3awnan.app.R;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private Button go_to_signup;
    private Button forget_pass;
    private Button login;
    private EditText inputEmail, inputPassword;
    private FirebaseFirestore db;
    private DocumentReference userRef;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    String email_db;
    String password_db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        // finding the views
        go_to_signup = findViewById(R.id.goToSignBtn);
        forget_pass = findViewById(R.id.forgetPassBtn);
        login = findViewById(R.id.loginBtn);
        inputEmail = findViewById(R.id.emailET);
        inputPassword = findViewById(R.id.passET);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();
        userRef = db.collection("users").document();


        //Get Firebase auth instance
        /*auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, Activity_Home.class));
            finish();
        }*/

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


        go_to_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent (Login.this, Signup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Email required!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Password required!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                } else {
                                    checkIfEmailVerified();
                                }
                            }});
            }
        });

    }
    // Check if the email is verified
    private void checkIfEmailVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            updateDB();
            startActivity(new Intent(Login.this, Home.class));
            finish();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(Login.this, "Email Not Verified yet, check your email for verification or sign up", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            FirebaseAuth.getInstance().signOut();
        }
    }

    private void updateDB(){

        userRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        // push into Firestore Database
        Map<String, Object> note = new HashMap<>();
        note.put("deviceToken", FirebaseInstanceId.getInstance().getToken());

        userRef.update(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Login.this, "User Added", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d("ERROR!", e.toString());
                    }
                });

    }
}
