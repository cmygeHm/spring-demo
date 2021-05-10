package com.example.demo.service;

import com.example.demo.dto.response.CalculationResult;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
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
                double sleepForDemo = (Math.random() * (30000)) + 5000;
                logger.info(String.format("Sleep %d seconds for demo", Math.round(sleepForDemo/1000)));
                Thread.sleep((long)sleepForDemo);
            } catch (InterruptedException e){
                logger.info("Thread was interrupted");
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
            logger.info(String.format("Task %s was processed", uuid));
        }
    }

    final private PersonRepository personRepository;
    final private TaskExecutor taskExecutor;
    final private Map<UUID, CalculationResult> calculatedTasks = new HashMap<>();
    final private static Logger logger = LoggerFactory.getLogger(TaskService.class);

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
        logger.info(String.format("Task %s was created", uuid));

        return uuid;
    }

    public Optional<CalculationResult> findTask(UUID uuid) {
        try {
            return Optional.of(calculatedTasks.get(uuid));
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    @Scheduled(cron="0 0 * * * ?")
    public void cleanOutdatedTasks()
    {
        logger.info("Start cleaning outdated calculations. Current HashMap size: " + calculatedTasks.size());
        Iterator iterator = calculatedTasks.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<UUID, CalculationResult> me = (Map.Entry)iterator.next();
            CalculationResult value = me.getValue();
            if (LocalDate.now().isAfter(value.getCreatedAt())) {
                logger.info("Remove outdated task: " + me.getKey());
                iterator.remove();
            }
        }
        logger.info("Finish cleaning outdated calculations. Current HashMap size: " + calculatedTasks.size());
    }
}
