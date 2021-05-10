package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate birthDay;
    @Transient
    private long daysBeforeBirthday;

    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Person setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public long getDaysBeforeBirthday() {
        return daysBeforeBirthday;
    }

    public Person setDaysBeforeBirthday(long daysBeforeBirthday) {
        this.daysBeforeBirthday = daysBeforeBirthday;
        return this;
    }
}
