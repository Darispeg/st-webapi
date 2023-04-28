package com.example.api_rest.service.event;

import com.example.api_rest.db.entities.Item;
import com.example.api_rest.service.GenericService;

import java.util.UUID;

public interface EventService extends GenericService<Item, UUID> {
    Item saveEvent(Item item);
    Item update(Item item);
}
