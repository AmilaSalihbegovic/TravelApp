package com.example.travelapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button reset;
    EditText emailReset;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        reset = findViewById(R.id.reset_btn);
        emailReset = findViewById(R.id.reset_email);
        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailReset.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(ForgotPasswordActivity.this, "You must enter an email!", Toast.LENGTH_SHORT).show();
                    emailReset.setError("Email is required");
                    emailReset.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(ForgotPasswordActivity.this, "You must enter an valid email!", Toast.LENGTH_SHORT).show();
                    emailReset.setError("Wrong email address");
                    emailReset.requestFocus();
                }else {
                    resetPassword(email);
                }
            }
        });

    }

    private void resetPassword(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Please check your email inbox for a reset link!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this, "Something went wrong, please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}