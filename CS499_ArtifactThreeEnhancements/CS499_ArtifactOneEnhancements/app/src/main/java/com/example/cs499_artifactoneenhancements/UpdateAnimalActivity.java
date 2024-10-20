package com.example.cs499_artifactoneenhancements;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateAnimalActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText sexEditText;
    private EditText breedEditText;
    private EditText ageEditText;
    private Button updateButton;
    private AnimalsDatabase animalsDatabase;
    private String animalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_animal);

        // Initialize the database
        animalsDatabase = new AnimalsDatabase(this);

        // Retrieve the animal ID from the intent (assuming it is passed from the previous activity)
        Intent intent = getIntent();
        animalId = intent.getStringExtra("ANIMAL_ID");

        nameEditText = findViewById(R.id.nameEditText);
        sexEditText = findViewById(R.id.sexEditText);
        breedEditText = findViewById(R.id.breedEditText);
        ageEditText = findViewById(R.id.ageEditText);
        updateButton = findViewById(R.id.updateButton);

        // Populate the fields with existing data
        populateFields();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnimal();
            }
        });
    }

    private void populateFields() {
        Cursor cursor = animalsDatabase.getAllData();
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(animalId)) {
                    nameEditText.setText(cursor.getString(1));
                    sexEditText.setText(cursor.getString(2));
                    breedEditText.setText(cursor.getString(3));
                    ageEditText.setText(cursor.getString(4));
                    break;
                }
            } while (cursor.moveToNext());
        }
    }

    private void updateAnimal() {
        String name = nameEditText.getText().toString().trim();
        String sex = sexEditText.getText().toString().trim();
        String breed = breedEditText.getText().toString().trim();
        String ageStr = ageEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(sex) && TextUtils.isEmpty(breed) && TextUtils.isEmpty(ageStr)) {
            Toast.makeText(this, "Please fill in at least one field to update", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the database
        boolean isUpdated = animalsDatabase.updateData(animalId, name, sex, breed, ageStr);

        if (isUpdated) {
            Toast.makeText(this, "Animal updated successfully!", Toast.LENGTH_SHORT).show();

            // Clear the fields after update
            nameEditText.setText("");
            sexEditText.setText("");
            breedEditText.setText("");
            ageEditText.setText("");
        } else {
            Toast.makeText(this, "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
