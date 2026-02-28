package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Produce;

public interface ProduceRepository extends CrudRepository<Produce, Long> {
    
}
