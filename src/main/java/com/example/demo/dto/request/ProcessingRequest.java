package com.example.demo.dto.request;

public class ProcessingRequest {
    private Integer monthToProcess = null;

    public Integer getMonthToProcess() {
        return monthToProcess;
    }

    public ProcessingRequest setMonthToProcess(Integer monthToProcess) {
        this.monthToProcess = monthToProcess;
        return this;
    }
}
