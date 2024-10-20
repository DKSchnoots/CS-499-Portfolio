package com.example.cs499_artifactoneenhancements;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button registerButton;
    UserDatabase dbHelper; // Initialize UserDatabase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Bind UI elements
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.registerButton);

        // Initialize database helper
        dbHelper = new UserDatabase(this);

        // Set onClickListener for registerButton
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate user input
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Username and password are required", Toast.LENGTH_SHORT).show();
                } else {
                    // Create User object and add to database
                    User user = new User(username, password);
                    dbHelper.addUser(user);
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity after registration
                }
            }
        });
    }
}
