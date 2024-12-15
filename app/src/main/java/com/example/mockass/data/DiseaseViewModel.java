package com.example.mockass.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DiseaseViewModel extends ViewModel {
    private MutableLiveData<List<DiseaseEntity>> diseasesLiveData;

    public DiseaseViewModel() {
        diseasesLiveData = new MutableLiveData<>();
        loadDiseases();
    }

    public LiveData<List<DiseaseEntity>> getDiseasesLiveData() {
        return diseasesLiveData;
    }

    private void loadDiseases() {
        // Example: Add some diseases manually
        List<DiseaseEntity> diseases = new ArrayList<>();
        diseases.add(new DiseaseEntity("Asthma", "A chronic condition characterized by difficulty breathing due to inflammation and narrowing of airways"));
        diseases.add(new DiseaseEntity("Chronic Obstructive Pulmonary Disease (COPD)", "A common lung disease causing restricted airflow and breathing problems"));
        diseases.add(new DiseaseEntity("Bronchiolitis", "Inflammation of bronchioles in the lungs"));
        diseases.add(new DiseaseEntity("Pneumonia", "An inflammatory condition of the lung primarily affecting the small air sacs"));
        diseases.add(new DiseaseEntity("Anemia", "A condition in which the number of red blood cells is lower than normal"));
        diseases.add(new DiseaseEntity("Irritable Bowel Disease (IBD)", "An inflammatory condition of the colon and small intestine"));
        diseases.add(new DiseaseEntity("Hypothyroidism", "A disorder of the endocrine system in which the thyroid gland does not produce enough thyroid hormones"));
        diseases.add(new DiseaseEntity("Angina", "Chest pain caused by insufficient blood flow to the heart muscle"));
        diseases.add(new DiseaseEntity( "Myocardial Infarction", "Commonly known as heart attack"));
        diseases.add(new DiseaseEntity(  "Gastroenteritis", "An inflammation of the gastrointestinal tract including stomach and intestine"));
        diseases.add(new DiseaseEntity( "Crohn's Disease", "A chronic inflammatory bowel disease "));
        diseases.add(new DiseaseEntity(  "Rheumatoid arthritis", "A chronic disease that causes inflammation around the body and commonly presents with pain in joints"));
        diseases.add(new DiseaseEntity( "Lupus", "A autoimmune disease in which the body's immune system mistakenly attacks healthy tissues in the body"));
        diseases.add(new DiseaseEntity( "Bronchitis", "A inflammation of the bronchi in the lungs that causes coughing"));
        diseases.add(new DiseaseEntity( "Celiac Disease", "A long-term autoimmune disorder, primarily affecting small intestine"));
        diseases.add(new DiseaseEntity( "Migraine", "A neurological disorder characterised by headache"));
        diseases.add(new DiseaseEntity("Malaria", "A life-threatening disease spread to humans by some types of mosquitoes"));
        diseases.add(new DiseaseEntity( "Typhoid","A disease caused by Salmonella enterica serotype Typhi bacteria"));
        diseasesLiveData.setValue(diseases);
    }
}
