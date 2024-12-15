package com.example.mockass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mockass.data.DiseaseAdapter;
import com.example.mockass.data.DiseaseViewModel;

import java.util.ArrayList;

public class ViewDisplay3 extends Fragment {

    private RecyclerView diseasesRecyclerView;
    private DiseaseAdapter diseaseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_display3, container, false);

        // Initialize RecyclerView
        diseasesRecyclerView = view.findViewById(R.id.diseasesRecyclerView);
        diseasesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        diseaseAdapter = new DiseaseAdapter(new ArrayList<>());
        diseasesRecyclerView.setAdapter(diseaseAdapter);

        // ViewModel setup
        DiseaseViewModel diseaseViewModel = new ViewModelProvider(this).get(DiseaseViewModel.class);

        // Observe disease list and update RecyclerView
        diseaseViewModel.getDiseasesLiveData().observe(getViewLifecycleOwner(), diseases -> {
            if (diseases != null) {
                diseaseAdapter.updateDiseases(diseases);
            }
        });

        // Handle Next button click
        Button nextButton = view.findViewById(R.id.next_button3);
        nextButton.setOnClickListener(v -> {
            // Navigate to next fragment or perform an action
            Navigation.findNavController(v).navigate(R.id.action_viewDisplayFragment_to_symptomViewHistory_reportFragment);
        });

        return view;
    }
}
