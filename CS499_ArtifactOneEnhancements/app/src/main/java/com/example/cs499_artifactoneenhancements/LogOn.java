package com.example.cs499_artifactoneenhancements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogOn extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton, createAccountButton;
    UserDatabase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_on);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        dbHelper = new UserDatabase(this);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(LogOn.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                if (validateLogin(username, password)) {
                    Toast.makeText(LogOn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent logIn = new Intent(LogOn.this, UserMainInterface.class);
                    startActivity(logIn);
                } else {
                    Toast.makeText(LogOn.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createAccountButton.setOnClickListener(v -> {
            Intent newAccount = new Intent(LogOn.this, RegistrationActivity.class);
            startActivity(newAccount);
        });
    }

    private boolean validateLogin(String username, String password) {
        // Implement actual login validation with UserDatabase
        return dbHelper.checkUser(username, password);
    }
}
