package com.example.lab7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
private void registerNewUser() {
    String username = usernameField.getText().toString().trim();
    String password = passwordField.getText().toString().trim();
    String confirmPassword = confirmPasswordField.getText().toString().trim();

    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
        showToast("Заполните все поля!");
        return;
    }

    if (!password.equals(confirmPassword)) {
        showToast("Пароли не совпадают!");
        return;
    }

    SharedPreferences prefs = getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);

    if (prefs.contains(username)) {
        showToast("Пользователь уже существует!");
        return;
    }

    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(username, password);
    editor.apply();

    // Проверяем, сохранился ли пользователь
    String checkPassword = prefs.getString(username, null);
    if (checkPassword != null) {
        showToast("Регистрация успешна!");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    } else {
        showToast("Ошибка при сохранении пользователя.");
    }
}