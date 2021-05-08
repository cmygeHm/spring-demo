package com.example.demo.repository;

import com.example.demo.entity.Task;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findOneByProcessedFalse();
}
