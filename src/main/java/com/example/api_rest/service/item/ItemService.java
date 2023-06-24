package com.example.api_rest.service.item;

import com.example.api_rest.db.entities.Item;
import com.example.api_rest.service.GenericService;

import java.util.List;
import java.util.UUID;

public interface ItemService extends GenericService<Item, UUID> {
    Item saveEvent(Item item);
    Item update(Item item);
    List<Item> findAllByCategory(String category);
}
