package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.entity.ProcessedTask;
import com.example.demo.entity.ProcessedTaskId;
import com.example.demo.entity.Task;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.ProcessedTaskRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    final private TaskRepository taskRepository;
    final private PersonRepository personRepository;
    final private ProcessedTaskRepository processedTaskRepository;

    public TaskService(
            TaskRepository taskRepository,
            PersonRepository personRepository,
            ProcessedTaskRepository processedTaskRepository
    ) {
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
        this.processedTaskRepository = processedTaskRepository;
    }

    public Long createTask(Integer monthToProcess) throws IllegalArgumentException {
        Task task = new Task();
        if (monthToProcess == null) {
            LocalDate now = LocalDate.now();
            task.setMonthToProcess(now.getMonthValue());
        } else if (1 < monthToProcess && 12 > monthToProcess){
            task.setMonthToProcess(monthToProcess);
        } else {
            throw new IllegalArgumentException("Invalid month: month value can be between 1 and 12");
        }
        taskRepository.save(task);

        return task.getId();
    }

    public void processTask(Task task) {
        List<Person> persons = personRepository.findAllByBirthdayMonth(task.getMonthToProcess());
        List<ProcessedTask> processedTasks = new ArrayList<>(persons.size());
        for(Person person: persons) {
            LocalDate birthday = person.getBirthDay();
            LocalDate birthdayInCurrentYear = birthday.withYear(LocalDate.now().getYear());
            LocalDate nextBirthday;
            if (birthdayInCurrentYear.isBefore(LocalDate.now())) {
                nextBirthday = birthdayInCurrentYear.plusYears(1L);
            } else {
                nextBirthday = birthdayInCurrentYear;
            }
            ProcessedTask processedTask = new ProcessedTask();
            ProcessedTaskId processedTaskId = new ProcessedTaskId();
            processedTaskId.setTask(task);
            processedTaskId.setPerson(person);
            processedTask.setProcessedTaskId(processedTaskId);
            processedTask.setNextBirthDay(nextBirthday);
            processedTasks.add(processedTask);
        }
        task.setProcessed(true);

        taskRepository.save(task);
        processedTaskRepository.saveAll(processedTasks);
    }
}