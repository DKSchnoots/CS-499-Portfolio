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
    private AnimalsDatabase animalsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_creation);

        nameEditText = findViewById(R.id.nameEditText);
        sexEditText = findViewById(R.id.sexEditText);
        breedEditText = findViewById(R.id.breedEditText);
        ageEditText = findViewById(R.id.ageEditText);
        saveButton = findViewById(R.id.saveButton);

        animalsDatabase = new AnimalsDatabase(this);

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

        boolean isInserted = animalsDatabase.insertData(name, sex, breed, String.valueOf(age));
        if (isInserted) {
            Toast.makeText(this, "Animal saved successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Failed to save animal", Toast.LENGTH_SHORT).show();
        }
    }
}
