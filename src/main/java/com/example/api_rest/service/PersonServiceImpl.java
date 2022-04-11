package com.example.api_rest.service;

import com.example.api_rest.Model.Person;
import com.example.api_rest.api.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public CrudRepository<Person, Long> getDao() {
        return repository;
    }
    
}
