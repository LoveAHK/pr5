package com.example.Rabota.repo;

import com.example.Rabota.Models.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Long> {
    University findByName(String name);
}
