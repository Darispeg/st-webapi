package com.example.api_rest.service.ticket;

import com.example.api_rest.db.entities.Ticket;
import com.example.api_rest.service.GenericService;

import java.util.List;
import java.util.UUID;

public interface TicketService extends GenericService<Ticket, UUID> {

    Ticket saveTicket(Ticket ticket);
    Ticket update(Ticket ticket);

    List<Ticket> findByEventKey(UUID eventKey);
}
