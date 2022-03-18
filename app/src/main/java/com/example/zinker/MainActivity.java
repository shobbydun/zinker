package com.example.zinker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText Name1,Email1,Pass1;
    ImageButton img_btn;
    FirebaseAuth fAuth;
    ProgressBar progressBar ;
    TextView textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name1 = findViewById(R.id.Name_1);
        Email1 = findViewById(R.id.Email_1);
        Pass1 = findViewById(R.id.Pass_1);
        img_btn = findViewById(R.id.Img_btn);
        Name1 = findViewById(R.id.Name_1);
        textLogin = findViewById(R.id.textView4);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null)
            startActivity(new Intent(getApplicationContext(),Log_in.class));


        img_btn.setOnClickListener(new View.OnClickListener() {
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

                //register user in firebase sasa
                fAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Welcome   💕 ",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else{
                            Toast.makeText(MainActivity.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT ).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });

            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Log_in.class));
            }
        });

    }
}

