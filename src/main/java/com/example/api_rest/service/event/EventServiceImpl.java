package com.example.api_rest.service.event;

import com.example.api_rest.api.ItemRepository;
import com.example.api_rest.db.entities.Item;
import com.example.api_rest.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventServiceImpl extends GenericServiceImpl<Item, UUID> implements EventService{

    @Autowired
    private ItemRepository repository;

    @Override
    public CrudRepository<Item, UUID> getDao() {
        return repository;
    }

    @Override
    public Item saveEvent(Item item) {
        item.setKey(UUID.randomUUID());
        item.setCreatedDate(LocalDateTime.now());
        return getDao().save(item);
    }

    @Override
    public Item update(Item item) {
        item.setLastModifiedDate(LocalDateTime.now());
        return getDao().save(item);
    }
}
