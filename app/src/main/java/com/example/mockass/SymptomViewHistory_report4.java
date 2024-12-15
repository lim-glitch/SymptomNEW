package com.example.mockass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mockass.data.SymptomEntity;
import com.example.mockass.data.SymptomHistoryAdapter;
import com.example.mockass.data.SymptomViewModel;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class SymptomViewHistory_report4 extends Fragment {
    private SymptomViewModel symptomViewModel; // ViewModel to fetch symptom data
    private List<String> symptomHistoryList = new ArrayList<>();


    public SymptomViewHistory_report4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize ViewModel
        symptomViewModel = new ViewModelProvider(this).get(SymptomViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_symptom_view_history_report4, container, false);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.symptomHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        SymptomHistoryAdapter adapter = new SymptomHistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Observe history data from ViewModel
        symptomViewModel.getSymptomHistory().observe(getViewLifecycleOwner(), history -> {
            if (history != null) {
                // Pass the list of SymptomEntity directly to the adapter
                adapter.updateData(history);

                // Create a List<String> for symptom names
                List<String> symptomNames = new ArrayList<>();
                for (SymptomEntity symptom : history) {
                    symptomNames.add(symptom.getName());
                }

                // Update symptomHistoryList with List<String>
                symptomHistoryList.clear();
                symptomHistoryList.addAll(symptomNames);
            }
        });




        // Generate Report button
        Button generateReportButton = view.findViewById(R.id.generateReportButton);
        generateReportButton.setOnClickListener(v -> {
            generateReport();
        });

        return view;
    }

    /**
     * Method to generate a symptom history report.
     */
    private void generateReport() {
        File reportFile = new File(requireContext().getFilesDir(), "symptom_history.txt");
        try (FileWriter writer = new FileWriter(reportFile)) {
            // Example content
            writer.write("Symptom History Report\n");
            writer.write("=======================\n");
            for (String symptom : symptomHistoryList) { // Assume you have a symptomHistoryList
                writer.write("- " + symptom + "\n");
            }
            Toast.makeText(requireContext(), "Report saved at: " + reportFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Failed to generate report.", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(requireContext(), "Report generated!", Toast.LENGTH_SHORT).show();
    }

}
