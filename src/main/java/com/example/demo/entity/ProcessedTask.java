package com.example.demo.entity;

import org.hibernate.annotations.Formula;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ProcessedTask {

    @EmbeddedId
    private ProcessedTaskId processedTaskId;

    private LocalDate nextBirthDay;

    @Formula("DATEDIFF('dd', now(), next_birth_day)")
    private Integer daysBeforeBirthday;

    public ProcessedTaskId getProcessedTaskId() {
        return processedTaskId;
    }

    public ProcessedTask setProcessedTaskId(ProcessedTaskId processedTaskId) {
        this.processedTaskId = processedTaskId;
        return this;
    }

    public LocalDate getNextBirthDay() {
        return nextBirthDay;
    }

    public ProcessedTask setNextBirthDay(LocalDate nextBirthDay) {
        this.nextBirthDay = nextBirthDay;
        return this;
    }

    public Integer getDaysBeforeBirthday() {
        return daysBeforeBirthday;
    }

    public ProcessedTask setDaysBeforeBirthday(Integer daysBeforeBirthday) {
        this.daysBeforeBirthday = daysBeforeBirthday;
        return this;
    }
}
