package com.example.mockass.data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockass.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder> {
    private List<SymptomEntity> symptomList;
    private Set<Integer> selectedPositions = new HashSet<>();  // Use a Set to track selected positions

    public SymptomAdapter(List<SymptomEntity> symptomList) {
        this.symptomList = symptomList;
    }

    @NonNull
    @Override
    public SymptomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_symptom, parent, false);
        return new SymptomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomViewHolder holder, int position) {
        SymptomEntity symptom = symptomList.get(position);
        holder.symptomName.setText(symptom.getName());

        // Change background color based on whether the item is selected
        if (selectedPositions.contains(position)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.pressedsecondarybutton));  // Selected color
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.secondarybutton));  // Default color
        }

        // Set OnClickListener to update selected items
        holder.itemView.setOnClickListener(v -> {
            int clickedPosition = holder.getAdapterPosition();
            if (clickedPosition != RecyclerView.NO_POSITION) {
                if (selectedPositions.contains(clickedPosition)) {
                    selectedPositions.remove(clickedPosition);  // Deselect if already selected
                } else {
                    selectedPositions.add(clickedPosition);  // Select if not selected
                }
                notifyDataSetChanged();  // Refresh the RecyclerView to update item backgrounds
            }
        });
    }

    @Override
    public int getItemCount() {
        return symptomList.size();
    }

    // This method is used to update the symptoms list and notify the adapter
    public void updateSymptoms(List<SymptomEntity> symptoms) {
        this.symptomList.clear();
        this.symptomList.addAll(symptoms);
        notifyDataSetChanged();
    }

    // Get selected symptoms (as SymptomEntity)
    public List<SymptomEntity> getSelectedSymptoms() {
        List<SymptomEntity> selectedSymptoms = new ArrayList<>();
        for (int position : selectedPositions) {
            selectedSymptoms.add(symptomList.get(position));  // Get the SymptomEntity at the selected position
        }
        return selectedSymptoms;
    }

    public static class SymptomViewHolder extends RecyclerView.ViewHolder {
        TextView symptomName;

        public SymptomViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomName = itemView.findViewById(R.id.tvSymptomName);
        }
    }
}
