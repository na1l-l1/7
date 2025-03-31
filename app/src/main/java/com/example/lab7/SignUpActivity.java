package com.example.lab7;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameField, passwordField, confirmPasswordField;
    private Button registerButton;
    private static final String USER_PREFS = "UserPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameField = findViewById(R.id.edUsername);
        passwordField = findViewById(R.id.edPassword);
        confirmPasswordField = findViewById(R.id.edConfirmPassword);
        registerButton = findViewById(R.id.btnCreateUser);

        registerButton.setOnClickListener(v -> registerNewUser());
    }
}