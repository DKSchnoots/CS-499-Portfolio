package com.example.cs499_artifactoneenhancements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder> {
    private List<Animal> animals;

    public AnimalsAdapter(List<Animal> animals) {
        this.animals = animals;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animals.get(position);
        holder.nameTextView.setText(animal.getName());
        holder.idTextView.setText(String.valueOf(animal.getId()));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView idTextView;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(android.R.id.text1);
            idTextView = itemView.findViewById(android.R.id.text2);
        }
    }
}

