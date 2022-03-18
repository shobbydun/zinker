package com.example.zinker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Log_in extends AppCompatActivity {

    EditText Email1,Pass1;
    TextView textView5;
    ImageButton login;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Email1 = findViewById(R.id.Email_1);
        Pass1 = findViewById(R.id.Pass_1);
        login = findViewById(R.id.imageButton);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        textView5 = findViewById(R.id.textView5);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email1.getText().toString().trim();
                String Password = Pass1.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Email1.setError("Please input Email");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Pass1.setError("Password is required");
                }
                if (Password.length()<6){
                    Pass1.setError("Password must be six characters");
                }

                progressBar.setVisibility(View.VISIBLE);

                //Authenticate the user
                fAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (task.isSuccessful()){
                                Toast.makeText(Log_in.this,"Logged in successfully Baby Girl 💕 ",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Home.class));
                            }else {
                                Toast.makeText(Log_in.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                });


                textView5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });


            }

        });}}


