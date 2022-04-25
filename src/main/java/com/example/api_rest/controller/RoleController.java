package com.example.api_rest.controller;

import java.util.List;

import com.example.api_rest.Model.Role;
import com.example.api_rest.service.role.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/roles")
@CrossOrigin(value = "*")
public class RoleController {
    
    @Autowired
    private RoleService _service;

    @GetMapping
    public List<Role> getAll()
    {
        return _service.findAll();
    }

    @PostMapping
    public ResponseEntity<Role> save(@RequestBody Role role)
    {
        Role obj = _service.saveRole(role);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
