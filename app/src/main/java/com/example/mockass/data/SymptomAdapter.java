package com.example.mockass.data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockass.R;

import java.util.List;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder> {
    private List<SymptomEntity> symptomList;
    private int selectedPosition =RecyclerView.NO_POSITION;

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
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.pressedsecondarybutton));  // Selected color
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.secondarybutton));  // Default color
        }

        // Set OnClickListener to update selected item
        holder.itemView.setOnClickListener(v -> {
            int clickedPosition = holder.getAdapterPosition();  // Use getAdapterPosition() to get updated position
            if (clickedPosition != RecyclerView.NO_POSITION) { // Ensure the position is valid
                selectedPosition = clickedPosition;
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
        this.symptomList.clear();  // Clear the old list
        this.symptomList.addAll(symptoms);  // Add the new list
        notifyDataSetChanged();  // Notify adapter of the change
    }

    public static class SymptomViewHolder extends RecyclerView.ViewHolder {
        TextView symptomName;

        public SymptomViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomName = itemView.findViewById(R.id.tvSymptomName);
        }
    }
}
