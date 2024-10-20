package com.example.cs499_artifactoneenhancements;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_animal);

        nameEditText = findViewById(R.id.nameEditText);
        sexEditText = findViewById(R.id.sexEditText);
        breedEditText = findViewById(R.id.breedEditText);
        ageEditText = findViewById(R.id.ageEditText);
        updateButton = findViewById(R.id.updateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAnimal();
            }
        });
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

        // Handle the update logic here (e.g., update the database, call an API, etc.)

        // Mock update success message
        Toast.makeText(this, "Animal updated successfully!", Toast.LENGTH_SHORT).show();

        // Clear the fields after update
        nameEditText.setText("");
        sexEditText.setText("");
        breedEditText.setText("");
        ageEditText.setText("");
    }
}
