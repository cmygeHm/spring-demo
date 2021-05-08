package com.example.demo.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ProcessingResult {
    private Boolean processed = false;

    private List<ProcessingPersonResult> persons;

    public ProcessingResult() {
         persons = new ArrayList<ProcessingPersonResult>();
    }

    public ProcessingResult(Integer capacity) {
         persons = new ArrayList<ProcessingPersonResult>(capacity);
    }

    public Boolean getProcessed() {
        return processed;
    }

    public ProcessingResult setProcessed(Boolean processed) {
        this.processed = processed;
        return this;
    }

    public Iterable<ProcessingPersonResult> getPersons() {
        return persons;
    }

    public ProcessingResult setPersons(List<ProcessingPersonResult> persons) {
        this.persons = persons;
        return this;
    }

    public ProcessingResult addPerson(ProcessingPersonResult person) {
        this.persons.add(person);
        return this;
    }
}
