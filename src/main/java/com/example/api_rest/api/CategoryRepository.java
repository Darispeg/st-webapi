package com.example.api_rest.api;

import com.example.api_rest.db.entities.Category;
import com.example.api_rest.db.entities.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends MongoRepository<Category, UUID> {
}
