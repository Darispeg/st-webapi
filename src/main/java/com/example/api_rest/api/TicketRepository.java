package com.example.api_rest.api;

import com.example.api_rest.db.entities.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, UUID> {
    List<Ticket> findByEventKey(UUID eventKey);
}
