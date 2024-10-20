package com.example.cs499_artifactoneenhancements;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAnimalActivity extends AppCompatActivity {

    private EditText nameEditText, sexEditText, breedEditText, ageEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_creation);

        nameEditText = findViewById(R.id.nameEditText);
        sexEditText = findViewById(R.id.sexEditText);
        breedEditText = findViewById(R.id.breedEditText);
        ageEditText = findViewById(R.id.ageEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnimal();
            }
        });
    }

    private void saveAnimal() {
        String name = nameEditText.getText().toString().trim();
        String sex = sexEditText.getText().toString().trim();
        String breed = breedEditText.getText().toString().trim();
        String ageStr = ageEditText.getText().toString().trim();

        if (name.isEmpty() || sex.isEmpty() || breed.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
            return;
        }

        // Logic to save the new animal (e.g., save to database or API)
        // For demonstration purposes, we'll just show a toast message
        String message = "Animal Saved:\nName: " + name + "\nSex: " + sex + "\nBreed: " + breed + "\nAge: " + age;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
