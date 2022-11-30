package com.example.Rabota.repo;

import com.example.Rabota.Models.Pasport;
import org.springframework.data.repository.CrudRepository;

public interface PasportRepository extends CrudRepository<Pasport, Long> {
    Pasport findByNumber(String number);
}
