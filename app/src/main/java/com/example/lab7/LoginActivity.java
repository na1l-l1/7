package com.example.lab7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button loginButton, registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.edUsername);
        passwordField = findViewById(R.id.edPassword);
        loginButton = findViewById(R.id.btnLogin);
        registerButton = findViewById(R.id.btnSignUp);

        registerButton.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));
        loginButton.setOnClickListener(v -> authenticateUser());
    }
