package com.example.cs499_artifactoneenhancements;

import android.database.Cursor;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_list);

        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        Button deleteButton = findViewById(R.id.deleteEntry);

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
        Cursor cursor = animalsDatabase.getAllData();
        animalList.clear();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(AnimalsDatabase.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(AnimalsDatabase.COL_NAME));
                // Add more fields if necessary
                animalList.add(new Animal(id, name));
            } while (cursor.moveToNext());
            cursor.close();
        }
        animalsAdapter.notifyDataSetChanged();
    }

    private void searchAnimals(String query) {
        Cursor cursor = animalsDatabase.getAllData();
        animalList.clear();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(AnimalsDatabase.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(AnimalsDatabase.COL_NAME));
                if (name.toLowerCase().contains(query.toLowerCase())) {
                    animalList.add(new Animal(id, name));
                }
            } while (cursor.moveToNext());
            cursor.close();
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
