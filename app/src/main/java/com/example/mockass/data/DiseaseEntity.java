package com.example.mockass.data;

public class DiseaseEntity {
    private String name;
    private String description;

    public DiseaseEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
