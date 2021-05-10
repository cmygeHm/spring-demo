package com.example.demo.dto.response;

import com.example.demo.entity.Person;

import java.time.LocalDate;
import java.util.List;

public class CalculationResult {
    final private LocalDate createdAt;
    private List<Person> persons;
    public CalculationResult(List<Person> persons) {
        this.createdAt = LocalDate.now();
        this.persons = persons;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
