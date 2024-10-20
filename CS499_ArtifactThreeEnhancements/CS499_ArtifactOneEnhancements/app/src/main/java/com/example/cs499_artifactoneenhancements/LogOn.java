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
    UserDatabase dbHelper; // Initialize UserDatabase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_on);

        // Bind UI elements
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Initialize database helper
        dbHelper = new UserDatabase(this);

        // Set onClickListener for loginButton
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Validate user input
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(LogOn.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                // Check login credentials
                if (validateLogin(username, password)) {
                    Toast.makeText(LogOn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent logIn = new Intent(LogOn.this, UserMainInterface.class);
                    startActivity(logIn);
                } else {
                    Toast.makeText(LogOn.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set onClickListener for createAccountButton
        createAccountButton.setOnClickListener(v -> {
            Intent newAccount = new Intent(LogOn.this, RegistrationActivity.class);
            startActivity(newAccount);
        });
    }

    // Validate user credentials against the database
    private boolean validateLogin(String username, String password) {
        return dbHelper.checkUser(username, password); // Call to UserDatabase
    }
}
