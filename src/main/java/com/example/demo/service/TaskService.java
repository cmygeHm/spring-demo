package com.example.demo.service;

import com.example.demo.dto.response.CalculationResult;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class TaskService {

    private class Task implements Runnable {
        final private UUID uuid;
        final private Integer month;
        private Map<UUID, CalculationResult> calculationResultMap;

        public Task(
                UUID uuid,
                Integer month,
                Map<UUID, CalculationResult> calculationResultMap
        ) {
            this.uuid = uuid;
            this.month = month;
            this.calculationResultMap = calculationResultMap;
        }

        public void run() {
            try {
                Thread.sleep((int) (Math.random() * (30000)) + 5000);
            } catch (InterruptedException e){
                System.out.println("Thread was Interrupted");
            }
            List<com.example.demo.entity.Person> persons = personRepository.findAllByBirthdayMonth(month);
            for(Person person: persons) {
                LocalDate birthday = person.getBirthDay();
                LocalDate birthdayInCurrentYear = birthday.withYear(LocalDate.now().getYear());
                LocalDate nextBirthday;
                if (birthdayInCurrentYear.isBefore(LocalDate.now())) {
                    nextBirthday = birthdayInCurrentYear.plusYears(1L);
                } else {
                    nextBirthday = birthdayInCurrentYear;
                }
                person.setDaysBeforeBirthday(
                    ChronoUnit.DAYS.between(LocalDate.now(), nextBirthday)
                );
            }
            CalculationResult result = new CalculationResult(persons);
            calculationResultMap.put(uuid, result);
        }
    }

    final private PersonRepository personRepository;
    final private TaskExecutor taskExecutor;
    final private Map<UUID, CalculationResult> calculatedTasks = new HashMap<>();

    public TaskService(
            PersonRepository personRepository,
            TaskExecutor taskExecutor
    ) {
        this.personRepository = personRepository;
        this.taskExecutor = taskExecutor;
    }

    public UUID createTask(Integer monthToProcess) throws IllegalArgumentException {

        if (monthToProcess == null) {
            LocalDate now = LocalDate.now();
            monthToProcess = now.getMonthValue();
        } else if (monthToProcess < 1 || monthToProcess > 12) {
            throw new IllegalArgumentException("Invalid month: month value can be between 1 and 12");
        }

        UUID uuid = UUID.randomUUID();
        Task runnable = new Task(
            uuid,
            monthToProcess,
            calculatedTasks
        );
        taskExecutor.execute(runnable);

        return uuid;
    }

    public Optional<CalculationResult> findTask(UUID uuid) {
        return Optional.of(calculatedTasks.get(uuid));
    }
}