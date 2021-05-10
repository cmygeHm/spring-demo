package com.example.demo.controller;

import com.example.demo.dto.request.ProcessingRequest;
import com.example.demo.dto.response.CalculationResult;
import com.example.demo.dto.response.CreatingResult;
import com.example.demo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import java.util.UUID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("/task")
public class TaskController {
    final private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    final private TaskService taskService;
    public TaskController(
        TaskService taskService
    ) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public CreatingResult createTask(@RequestBody ProcessingRequest createRequest) {
        try {
            return new CreatingResult(
                taskService.createTask(createRequest.getMonthToProcess())
            );
        } catch (IllegalArgumentException ex) {
            logger.warn("Bad request: " + ex.getMessage());
            throw new ResponseStatusException(BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/check")
    public CalculationResult checkTask(@RequestParam UUID uuid) {
        Optional<CalculationResult> optionalTask = taskService.findTask(uuid);

        if (optionalTask.isEmpty()) {
            logger.info("Task not found: " + uuid);
            throw new ResponseStatusException(NOT_FOUND, "Task not found");
        }

        return optionalTask.get();
    }

    @GetMapping("/clean")
    public void cleanOutdatedCalculations() {
        taskService.cleanOutdatedTasks();
    }
}
