package com.example.mockass.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockass.R;

import java.util.List;

public class SymptomHistoryAdapter extends RecyclerView.Adapter<SymptomHistoryAdapter.ViewHolder> {
    private List<SymptomEntity> symptoms;

    public SymptomHistoryAdapter(List<SymptomEntity> symptoms) {
        this.symptoms = symptoms;
    }

    public void updateData(List<SymptomEntity> newSymptoms) {
        this.symptoms = newSymptoms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_symptom_history_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SymptomEntity symptom = symptoms.get(position);
        holder.symptomNameTextView.setText(symptom.getName());
    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView symptomNameTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomNameTextView = itemView.findViewById(R.id.symptomNameTextView);
        }
    }
}
