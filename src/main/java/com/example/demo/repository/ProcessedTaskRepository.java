package com.example.demo.repository;

import com.example.demo.entity.ProcessedTask;
import com.example.demo.entity.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProcessedTaskRepository extends CrudRepository<ProcessedTask, Long> {
    List<ProcessedTask> findAllByProcessedTaskId_Task(Task task);
}
