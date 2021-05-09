package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE FUNCTION('MONTH', p.birthDay) = ?1")
    List<Person> findAllByBirthdayMonth(Integer month);
}
