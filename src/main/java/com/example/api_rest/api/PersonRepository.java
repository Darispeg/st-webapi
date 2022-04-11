package com.example.api_rest.api;

import com.example.api_rest.Model.Person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, Long> {
    
}
