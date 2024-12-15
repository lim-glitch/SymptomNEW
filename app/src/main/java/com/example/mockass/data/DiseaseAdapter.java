package com.example.mockass.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockass.R;

import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder> {

    private List<DiseaseEntity> diseaseList;

    public DiseaseAdapter(List<DiseaseEntity> diseaseList) {
        this.diseaseList = diseaseList;
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        DiseaseEntity disease = diseaseList.get(position);
        holder.diseaseNameTextView.setText(disease.getName());
        holder.diseaseDescriptionTextView.setText(disease.getDescription());
    }

    @Override
    public int getItemCount() {
        return diseaseList.size();
    }

    public static class DiseaseViewHolder extends RecyclerView.ViewHolder {
        TextView diseaseNameTextView;
        TextView diseaseDescriptionTextView;

        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
            diseaseNameTextView = itemView.findViewById(R.id.diseaseNameTextView);
            diseaseDescriptionTextView = itemView.findViewById(R.id.diseaseDescriptionTextView);
        }
    }

    public void updateDiseases(List<DiseaseEntity> newDiseases) {
        diseaseList.clear();
        diseaseList.addAll(newDiseases);
        notifyDataSetChanged();
    }
}
