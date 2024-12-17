package com.example.mockass.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SymptomViewModel extends ViewModel {

    private final SymptomDao symptomDao;
    private final LiveData<List<SymptomEntity>> symptomHistory;

    public SymptomViewModel(SymptomDao symptomDao) {
        this.symptomDao = symptomDao;
        // Fetch symptom history from the database
        this.symptomHistory = symptomDao.getAllSymptoms();
    }

    public void insertSymptoms() {
        SymptomEntity[] symptoms = new SymptomEntity[] {
                new SymptomEntity("Wheezing"),
                new SymptomEntity("Shortness of breath"),
                new SymptomEntity("Constipation"),
                new SymptomEntity("Chest pain"),
                new SymptomEntity("Abdominal pain"),
                new SymptomEntity("Chronic joint pain"),
                new SymptomEntity("Nausea"),
                new SymptomEntity("Inflammation"),
                new SymptomEntity("Coughing"),
                new SymptomEntity("Diarrhea"),
                new SymptomEntity("Fever")
        };

        // Insert symptoms asynchronously
        new Thread(() -> {
            for (SymptomEntity symptom : symptoms) {
                // Check if the symptom already exists
                SymptomEntity existingSymptom = symptomDao.findByName(symptom.getName());
                if (existingSymptom == null) {
                    // If not, insert it
                    symptomDao.insert(symptom);
                }
            }
        }).start();
    }

    public LiveData<List<SymptomEntity>> getSymptomsLiveData() {
        return symptomDao.getAllSymptoms();
    }

    public LiveData<List<SymptomEntity>> getSymptomHistory(List<String> selectedSymptomNames) {
        return symptomDao.getSymptomHistory(selectedSymptomNames);
    }
}
