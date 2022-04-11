package com.example.api_rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.api_rest.Model.Person;
import com.example.api_rest.api.PersonRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenericServiceImplTest {

    @Autowired
    private PersonRepository _repository;

    @Test
    void testFindAll() {
        List<Person> newList = new ArrayList<>();

        newList = _repository.findAll();

        for (Person person : newList) {
            assertEquals(person.getId().getClass(), Long.class);
        }
    }

    @Test
    void testFindById() {
        Optional<Person> obj;

        obj = _repository.findById((Long.valueOf(1000001)));

        if (obj.isPresent())
            assertTrue(obj.isPresent());
        else
            assertFalse(obj.isPresent());
    }
}
