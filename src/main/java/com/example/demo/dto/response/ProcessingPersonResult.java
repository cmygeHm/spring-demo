package com.example.demo.dto.response;

public class ProcessingPersonResult {
    private String name;
    private Integer daysBeforeBirthday;

    public String getName() {
        return name;
    }

    public ProcessingPersonResult setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDaysBeforeBirthday() {
        return daysBeforeBirthday;
    }

    public ProcessingPersonResult setDaysBeforeBirthday(Integer daysBeforeBirthday) {
        this.daysBeforeBirthday = daysBeforeBirthday;
        return this;
    }
}
