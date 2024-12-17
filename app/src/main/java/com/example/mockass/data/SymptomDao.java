package com.example.mockass.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SymptomDao {

    @Insert
    void insert(SymptomEntity symptom);

    @Insert
    void insertAll(SymptomEntity... symptoms);

    @Query("SELECT * FROM SymptomEntity WHERE name = :name LIMIT 1")
    SymptomEntity findByName(String name);

    @Query("SELECT * FROM SymptomEntity")
    LiveData<List<SymptomEntity>> getAllSymptoms();

    @Query("SELECT * FROM SymptomEntity WHERE name IN (:names)")
    LiveData<List<SymptomEntity>> getSymptomHistory(List<String> names);
}
