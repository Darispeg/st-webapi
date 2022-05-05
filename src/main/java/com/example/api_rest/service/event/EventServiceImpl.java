package com.example.api_rest.service.event;

import com.example.api_rest.api.EventRepository;
import com.example.api_rest.db.entities.Event;
import com.example.api_rest.db.entities.Role;
import com.example.api_rest.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventServiceImpl extends GenericServiceImpl<Event, UUID> implements EventService{

    @Autowired
    private EventRepository repository;

    @Override
    public CrudRepository<Event, UUID> getDao() {
        return repository;
    }

    @Override
    public Event saveEvent(Event event) {
        event.setKey(UUID.randomUUID());
        event.setCreatedDate(LocalDateTime.now());
        return getDao().save(event);
    }

    @Override
    public Event update(Event event) {
        event.setLastModifiedDate(LocalDateTime.now());
        return getDao().save(event);
    }
}
