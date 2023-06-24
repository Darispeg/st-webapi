package com.example.api_rest.service.category;

import com.example.api_rest.db.entities.Category;
import com.example.api_rest.db.entities.Sale;
import com.example.api_rest.service.GenericService;

import java.util.UUID;

public interface CategoryService extends GenericService<Category, UUID> {

    Category saveCategory(Category category);
}
