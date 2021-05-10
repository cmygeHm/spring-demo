package com.example.demo.dto.response;

import com.example.demo.entity.Person;

import java.time.LocalDateTime;
import java.util.List;

public class CalculationResult {
    final private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime calculatedAt;
    private boolean processed = false;
    private List<Person> persons;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CalculationResult setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
        return this;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public CalculationResult markAsProcessed() {
        this.processed = true;
        return this;
    }

    public boolean isProcessed() {
        return processed;
    }

    public CalculationResult setPersons(List<Person> persons) {
        this.persons = persons;
        return this;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
