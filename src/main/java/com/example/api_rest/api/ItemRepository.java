package com.example.api_rest.api;

import com.example.api_rest.db.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends MongoRepository<Item, UUID> {
    List<Item> findAllByCategory(String category);
}
