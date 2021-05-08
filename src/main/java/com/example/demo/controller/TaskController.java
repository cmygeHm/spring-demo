package com.example.demo.controller;

import com.example.demo.dto.request.ProcessingRequest;
import com.example.demo.dto.response.CreatingResult;
import com.example.demo.dto.response.ProcessingPersonResult;
import com.example.demo.dto.response.ProcessingResult;
import com.example.demo.entity.ProcessedTask;
import com.example.demo.entity.ProcessedTaskId;
import com.example.demo.entity.Task;
import com.example.demo.repository.ProcessedTaskRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("/task")
public class TaskController {

    final private TaskService taskService;
    final private TaskRepository taskRepository;
    final private ProcessedTaskRepository processedTaskRepository;

    public TaskController(
        TaskService taskService,
        TaskRepository taskRepository,
        ProcessedTaskRepository processedTaskRepository
    ) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.processedTaskRepository = processedTaskRepository;
    }

    @PostMapping("/create")
    public CreatingResult createTask(@RequestBody ProcessingRequest createRequest) {
        try {
            return new CreatingResult(
                taskService.createTask(createRequest.getMonthToProcess())
            );
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(BAD_REQUEST, ex.getMessage());
        }

    }

    @GetMapping("/check")
    public ProcessingResult checkTask(@RequestParam Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Task not found");
        }

        if (!optionalTask.get().getProcessed()) {
            return new ProcessingResult();
        }

        ProcessedTaskId processedTaskId = new ProcessedTaskId();
        processedTaskId.setTask(optionalTask.get());
        List<ProcessedTask> processedTasks = processedTaskRepository.findAllByProcessedTaskId_Task(optionalTask.get());
        if (processedTasks.isEmpty()) {
            ProcessingResult processingResult = new ProcessingResult();
            processingResult.setProcessed(true);
            return processingResult;
        }

        ProcessingResult result = new ProcessingResult(processedTasks.size());
        result.setProcessed(optionalTask.get().getProcessed());
        for(ProcessedTask processedTask: processedTasks) {
            ProcessingPersonResult personResult = new ProcessingPersonResult();
            personResult.setName(processedTask.getProcessedTaskId().getPerson().getName());
            personResult.setDaysBeforeBirthday(processedTask.getDaysBeforeBirthday());
            result.addPerson(personResult);
        }

        return result;
    }
}
