package com.example.mockass.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SymptomViewModelFactory implements ViewModelProvider.Factory {

    private SymptomDao symptomDao;

    public SymptomViewModelFactory(SymptomDao symptomDao) {
        this.symptomDao = symptomDao;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SymptomViewModel.class)) {
            return (T) new SymptomViewModel(symptomDao);  // Safe cast now
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

