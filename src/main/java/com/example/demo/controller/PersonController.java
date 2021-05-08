package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    final private PersonRepository personRepository;

    public PersonController(
        PersonRepository personRepository
    ) {
        this.personRepository = personRepository;
    }

    @GetMapping()
    public Iterable<Person> getUsers() {
        return personRepository.findAll();
    }
}
