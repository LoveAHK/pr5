package com.example.Rabota.repo;

import com.example.Rabota.Models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
