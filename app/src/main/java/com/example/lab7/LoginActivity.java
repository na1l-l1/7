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
    private static final String USER_PREFS = "UserPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.edUsername);
        passwordField = findViewById(R.id.edPassword);
        loginButton = findViewById(R.id.btnLogin);
        registerButton = findViewById(R.id.btnSignUp);

        registerButton.setOnClickListener(v -> startActivity(new Intent(this, com.example.lab07.SignUpActivity.class)));
        loginButton.setOnClickListener(v -> authenticateUser());
    }

    private void authenticateUser() {
        String user = usernameField.getText().toString().trim();
        String pass = passwordField.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            showToast("Введите логин и пароль");
            return;
        }

        SharedPreferences prefs = getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        if (!prefs.contains(user)) {
            showToast("Пользователь не найден. Зарегистрируйтесь.");
            return;
        }

        if (pass.equals(prefs.getString(user, ""))) {
            showToast("Вход успешен!");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            showToast("Неверный логин или пароль");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
