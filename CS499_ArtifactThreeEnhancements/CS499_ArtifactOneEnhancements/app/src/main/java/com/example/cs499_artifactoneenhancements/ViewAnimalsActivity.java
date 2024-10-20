package com.example.cs499_artifactoneenhancements;
import com.example.cs499_artifactoneenhancements.AnimalsDatabase;


import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAnimalsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimalsAdapter animalsAdapter;
    private List<Animal> animalList;
    private AnimalsDatabase animalsDatabase;
    private SearchView searchBar;
    private EditText idEditText; // Added EditText for animal ID input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_list); // Ensure this matches your XML layout name

        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        idEditText = findViewById(R.id.idEditText); // Initialize idEditText
        Button deleteButton = findViewById(R.id.deleteEntry); // Ensure deleteEntry button exists in XML

        // Initialize the database and animal list
        animalsDatabase = new AnimalsDatabase(this);
        animalList = new ArrayList<>();
        animalsAdapter = new AnimalsAdapter(animalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(animalsAdapter);

        // Load all animals initially
        loadAnimals();

        // Set up the search bar listener
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAnimals(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAnimals(newText);
                return false;
            }
        });

        // Set up the delete button click listener
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = idEditText.getText().toString();
                if (!idString.isEmpty()) {
                    deleteAnimalById(idString);
                }
            }
        });
    }

    private void loadAnimals() {
        Cursor cursor = null;
        try {
            cursor = animalsDatabase.getAllData();
            animalList.clear();
            if (cursor != null) {
                int idIndex = cursor.getColumnIndex(AnimalsDatabase.COL_ID);
                int nameIndex = cursor.getColumnIndex(AnimalsDatabase.COL_NAME);

                // Check if the column indexes are valid
                if (idIndex == -1 || nameIndex == -1) {
                    Log.e("ViewAnimalsActivity", "Column index not found. Check your database schema.");
                    return; // Early exit if column index is invalid
                }

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(idIndex);
                        String name = cursor.getString(nameIndex);
                        // Add more fields if necessary
                        animalList.add(new Animal(id, name));
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Ensure to close the cursor
            }
        }
        animalsAdapter.notifyDataSetChanged();
    }

    private void searchAnimals(String query) {
        Cursor cursor = null;
        try {
            cursor = animalsDatabase.getAllData();
            animalList.clear();
            if (cursor != null) {
                int idIndex = cursor.getColumnIndex(AnimalsDatabase.COL_ID);
                int nameIndex = cursor.getColumnIndex(AnimalsDatabase.COL_NAME);

                // Check if the column indexes are valid
                if (idIndex == -1 || nameIndex == -1) {
                    Log.e("ViewAnimalsActivity", "Column index not found. Check your database schema.");
                    return; // Early exit if column index is invalid
                }

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(idIndex);
                        String name = cursor.getString(nameIndex);
                        if (name.toLowerCase().contains(query.toLowerCase())) {
                            animalList.add(new Animal(id, name));
                        }
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Ensure to close the cursor
            }
        }
        animalsAdapter.notifyDataSetChanged();
    }

    private void deleteAnimalById(String idString) {
        int result = animalsDatabase.deleteData(idString);
        if (result > 0) {
            loadAnimals(); // Reload animals after deletion
        }
    }
}
