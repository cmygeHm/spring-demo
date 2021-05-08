package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private Integer monthToProcess;

    private Boolean processed = false;

    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getMonthToProcess() {
        return monthToProcess;
    }

    public Task setMonthToProcess(Integer monthToProcess) {
        this.monthToProcess = monthToProcess;
        return this;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public Task setProcessed(Boolean processed) {
        this.processed = processed;
        return this;
    }
}
