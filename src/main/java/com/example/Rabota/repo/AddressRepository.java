package com.example.Rabota.repo;

import com.example.Rabota.Models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Address findByStreet(String street);
}
