package com.example.api_rest.controller;

import java.util.List;

import com.example.api_rest.Model.Person;
import com.example.api_rest.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/person")
@CrossOrigin(value = "*")
public class PersonController {
    
    @Autowired
    private PersonService _personService;

    @GetMapping(value = "/all")
    public List<Person> getAll(){
        return _personService.findAll();
    }

    @GetMapping(value = "/find/{id}")
    public Person getPerson(@PathVariable Long id){
        return _personService.findById(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Person> save(@RequestBody Person person)
    {
        Person obj = _personService.save(person);
        return new ResponseEntity<Person>(obj, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Person> delete(@PathVariable Long id) {
        Person person = _personService.findById(id);

        if(person != null)
            _personService.delete(id);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }
}
