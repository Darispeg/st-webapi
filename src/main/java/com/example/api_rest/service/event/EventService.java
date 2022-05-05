package com.example.api_rest.service.event;

import com.example.api_rest.db.entities.Event;
import com.example.api_rest.service.GenericService;

import java.util.UUID;

public interface EventService extends GenericService<Event, UUID> {
    Event saveEvent(Event event);
    Event update(Event event);
}
