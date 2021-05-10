package com.example.demo.dto.response;

import java.util.UUID;

public class CreatingResult {
    private UUID taskId;

    public CreatingResult(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getTaskId() {
        return taskId;
    }
}
