package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostConstructService {

    final private PersonRepository personRepository;

    public PostConstructService(
        PersonRepository personRepository
    ) {
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void init() {
        LocalDate start = LocalDate.now().minusYears(1);
        LocalDate end = LocalDate.now();
        EasyRandomParameters parameters = new EasyRandomParameters()
            .dateRange(start, end);
        EasyRandom generator = new EasyRandom(parameters);
        List<Person> persons = generator.objects(Person.class, 5000)
            .collect(Collectors.toList());

        personRepository.saveAll(persons);
    }
}
