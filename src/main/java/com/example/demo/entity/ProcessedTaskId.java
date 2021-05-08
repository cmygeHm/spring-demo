package com.example.demo.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProcessedTaskId implements Serializable {

    @ManyToOne
    private Person person;

    @ManyToOne
    private Task task;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedTaskId that = (ProcessedTaskId) o;
        return Objects.equals(person, that.person) && Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, task);
    }

    public Person getPerson() {
        return person;
    }

    public ProcessedTaskId setPerson(Person person) {
        this.person = person;
        return this;
    }

    public Task getTask() {
        return task;
    }

    public ProcessedTaskId setTask(Task task) {
        this.task = task;
        return this;
    }
}
