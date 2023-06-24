package com.example.api_rest.controller;

import com.example.api_rest.db.entities.Category;
import com.example.api_rest.db.entities.Sale;
import com.example.api_rest.db.entities.User;
import com.example.api_rest.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService _categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll()
    {
        return ResponseEntity.ok(_categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/categories").toUriString());
        return ResponseEntity.created(uri).body(_categoryService.saveCategory(category));
    }

}
