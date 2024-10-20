package com.example.cs499_artifactoneenhancements;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserMainInterface extends AppCompatActivity {
    Button viewAnimalsButton, createAnimalButton, updateAnimalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_interface);

        // Initialize buttons
        viewAnimalsButton = findViewById(R.id.read);
        createAnimalButton = findViewById(R.id.create);
        updateAnimalButton = findViewById(R.id.update);

        // Set onClickListeners for the buttons
        viewAnimalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start activity to view all shelter animals
                Intent viewEntries = new Intent(UserMainInterface.this, ViewAnimalsActivity.class);
                startActivity(viewEntries);
            }
        });

        createAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start activity to create a new shelter animal
                Intent createEntry = new Intent(UserMainInterface.this, CreateAnimalActivity.class);
                startActivity(createEntry);
            }
        });

        updateAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start activity to update a shelter animal
                Intent updateEntry = new Intent(UserMainInterface.this, UpdateAnimalActivity.class);
                startActivity(updateEntry);
            }
        });
    }
}
