package com.example.mockass;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mockass.data.AppDatabase;
import com.example.mockass.data.SymptomEntity;
import com.example.mockass.data.SymptomViewModel;
import com.example.mockass.data.SymptomViewModelFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView tabSymptom, tabFactors, tabCauses;
    private ImageView backButton;
    private AppDatabase database;
    private SymptomViewModel symptomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate completed");

        // Initialize ViewModel
        database = AppDatabase.getInstance(this);
        symptomViewModel = new ViewModelProvider(this, new SymptomViewModelFactory(database.symptomDao())).get(SymptomViewModel.class);

        // Insert sample symptoms
        symptomViewModel.insertSymptoms();

        // Observe symptoms from ViewModel
        symptomViewModel.getSymptomsLiveData().observe(this, symptoms -> {
            if (symptoms != null) {
                updateSymptomsUI(symptoms);
            }
        });

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backButton = findViewById(R.id.toolbar_backButton);
        backButton.setOnClickListener(view -> onBackPressed());

        // Set up NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Tabs Setup
        tabSymptom = findViewById(R.id.tab_symptom);
        tabFactors = findViewById(R.id.tab_factors);
        tabCauses = findViewById(R.id.tab_causes);

        tabSymptom.setOnClickListener(v -> navigateToFragment(navController, R.id.symptomSelectionFragment));
        tabFactors.setOnClickListener(v -> navigateToFragment(navController, R.id.relatedFactorsFragment));
        tabCauses.setOnClickListener(v -> navigateToFragment(navController, R.id.viewDisplayFragment));

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // Handle bottom navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_item_home) {
                navigateToFragment(navController, R.id.symptomSelectionFragment);
                return true;
            }
            return false;
        });

        // Initially load the symptom fragment
        if (savedInstanceState == null) {
            navigateToFragment(navController, R.id.symptomSelectionFragment);
        }
    }

    private void navigateToFragment(NavController navController, int fragmentId) {
        navController.navigate(fragmentId);
        updateTabAppearance(fragmentId);
    }

    private void updateTabAppearance(int selectedTabId) {
        // Update tab appearance based on selected tab
        tabSymptom.setTextColor(selectedTabId == R.id.symptomSelectionFragment ?
                ContextCompat.getColor(this, R.color.whiteFont) :
                ContextCompat.getColor(this, R.color.blackFont));
        tabFactors.setTextColor(selectedTabId == R.id.relatedFactorsFragment ?
                ContextCompat.getColor(this, R.color.whiteFont) :
                ContextCompat.getColor(this, R.color.blackFont));
        tabCauses.setTextColor(selectedTabId == R.id.viewDisplayFragment ?
                ContextCompat.getColor(this, R.color.whiteFont) :
                ContextCompat.getColor(this, R.color.blackFont));

        // Reset the background of the tabs
        tabSymptom.setBackgroundResource(selectedTabId == R.id.symptomSelectionFragment ?
                R.color.pressedprimarybutton : 0);
        tabFactors.setBackgroundResource(selectedTabId == R.id.relatedFactorsFragment ?
                R.color.pressedprimarybutton : 0);
        tabCauses.setBackgroundResource(selectedTabId == R.id.viewDisplayFragment ?
                R.color.pressedprimarybutton : 0);

        // Set bold for the selected tab
        tabSymptom.setTypeface(null, selectedTabId == R.id.symptomSelectionFragment ? Typeface.BOLD : Typeface.NORMAL);
        tabFactors.setTypeface(null, selectedTabId == R.id.relatedFactorsFragment ? Typeface.BOLD : Typeface.NORMAL);
        tabCauses.setTypeface(null, selectedTabId == R.id.viewDisplayFragment ? Typeface.BOLD : Typeface.NORMAL);
    }

    private void updateSymptomsUI(List<SymptomEntity> symptoms) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("symptoms", new ArrayList<>(symptoms));
        SymptomSelection symptomSelectionFragment = new SymptomSelection();
        symptomSelectionFragment.setArguments(bundle);

        // Update UI by navigating to symptom fragment
        navigateToFragment(Navigation.findNavController(this, R.id.nav_host_fragment), R.id.symptomSelectionFragment);
    }
}
