package com.example.mockass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockass.data.AppDatabase;
import com.example.mockass.data.SymptomAdapter;
import com.example.mockass.data.SymptomEntity;
import com.example.mockass.data.SymptomViewModel;
import com.example.mockass.data.SymptomViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class SymptomSelection extends Fragment {

    private SymptomViewModel symptomViewModel;
    private SymptomAdapter symptomAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symptom_selection, container, false);

        // RecyclerView setup
        RecyclerView recyclerView = view.findViewById(R.id.symptom_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns for GridView
        symptomAdapter = new SymptomAdapter(new ArrayList<>());
        recyclerView.setAdapter(symptomAdapter);

        // ViewModel setup
        AppDatabase database = AppDatabase.getInstance(requireContext());
        symptomViewModel = new ViewModelProvider(this, new SymptomViewModelFactory(database.symptomDao()))
                .get(SymptomViewModel.class);

        // Observe data and update adapter
        symptomViewModel.getSymptomsLiveData().observe(getViewLifecycleOwner(), symptoms -> {
            if (symptoms != null) {
                symptomAdapter.updateSymptoms(symptoms);
            }
        });

        Button submitButton = view.findViewById(R.id.submit_button1);
        View.OnClickListener button = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the fragment transaction
                Fragment relatedFactorsFragment = new RelatedFactorsSelection2();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

                // Replace the current fragment with the new fragment
                transaction.replace(R.id.fragment_container, relatedFactorsFragment);
                transaction.addToBackStack(null); // Optional: adds to the back stack for "Back" navigation
                transaction.commit(); // Commit the transaction

                // This ensures the tab appearance is updated after navigating
                ((MainActivity) getActivity()).updateTabAppearance(relatedFactorsFragment);
            }
        };

        submitButton.setOnClickListener(button);

        return view;
    }
}
