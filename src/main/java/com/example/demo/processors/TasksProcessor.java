package com.example.demo.processors;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;

import java.util.Optional;

public class TasksProcessor implements Runnable {

    final private TaskService taskService;
    final private TaskRepository taskRepository;

    public TasksProcessor(
            TaskRepository taskRepository,
            TaskService taskService
    ) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            while (true) {
                Optional<Task> optionalTask = taskRepository.findOneByProcessedFalse();
                if (optionalTask.isPresent()) {
                    taskService.processTask(optionalTask.get());
                    System.out.printf("Task %d was processed... \n", optionalTask.get().getId());
                } else {
                    System.out.println("No tasks to process... \n");
                }

                Thread.sleep((int) (Math.random() * (30000)) + 5000);
            }
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
