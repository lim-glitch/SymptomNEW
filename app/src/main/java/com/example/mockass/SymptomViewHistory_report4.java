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

import com.example.mockass.data.AppDatabase;
import com.example.mockass.data.SymptomDao;
import com.example.mockass.data.SymptomEntity;
import com.example.mockass.data.SymptomHistoryAdapter;
import com.example.mockass.data.SymptomViewModel;
import com.example.mockass.data.SymptomViewModelFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class SymptomViewHistory_report4 extends Fragment {
    private SymptomViewModel symptomViewModel; // ViewModel to fetch symptom data
    private SymptomHistoryAdapter adapter;


    public SymptomViewHistory_report4() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get SymptomDao from your database instance
        AppDatabase db = AppDatabase.getInstance(requireActivity().getApplication());
        SymptomDao symptomDao = db.symptomDao();

        // Step 2: Pass SymptomDao to SymptomViewModelFactory
        SymptomViewModelFactory factory = new SymptomViewModelFactory(symptomDao);

        // Step 3: Use ViewModelProvider with the factory
        symptomViewModel = new ViewModelProvider(this, factory).get(SymptomViewModel.class);

        // Insert predefined symptoms (run once)
        symptomViewModel.insertSymptoms();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_symptom_view_history_report4, container, false);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.symptomHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SymptomHistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Retrieve selected symptoms passed from another fragment
        /*List<String> selectedSymptoms = getArguments() != null ? getArguments().getStringArrayList("selectedSymptoms") : new ArrayList<>();


        if (selectedSymptoms != null && !selectedSymptoms.isEmpty()) {
            // Fetch and observe only the selected symptoms
            symptomViewModel.getSymptomHistory(selectedSymptoms).observe(getViewLifecycleOwner(), history -> {
                if (history != null) {
                    adapter.updateData(history);
                }
            });
        } else {
            // If no selected symptoms are passed, show a message
            Toast.makeText(requireContext(), "No symptoms selected!", Toast.LENGTH_SHORT).show();
        }*/
        // Create mock symptoms list for testing purposes
        List<SymptomEntity> mockSymptoms = new ArrayList<>();
        mockSymptoms.add(new SymptomEntity("Wheezing"));
        mockSymptoms.add(new SymptomEntity("Constipation"));
        mockSymptoms.add(new SymptomEntity("Fever"));
        mockSymptoms.add(new SymptomEntity("Fatigue"));
        mockSymptoms.add(new SymptomEntity("Nausea"));
        adapter.updateData(mockSymptoms);

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
            // Get the symptoms from the adapter (which is populated by the ViewModel)
            List<SymptomEntity> symptomHistory = adapter.getSymptoms();

            // Write each symptom to the report
            for (SymptomEntity symptom : symptomHistory) {
                writer.write("- " + symptom.getName() + "\n");
            }
            Toast.makeText(requireContext(), "Report saved at: " + reportFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Failed to generate report.", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(requireContext(), "Report generated!", Toast.LENGTH_SHORT).show();
    }

}
