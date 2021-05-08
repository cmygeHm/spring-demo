package com.example.demo.dto.response;

public class CreatingResult {
    private Long taskId;

    public CreatingResult(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }
}
