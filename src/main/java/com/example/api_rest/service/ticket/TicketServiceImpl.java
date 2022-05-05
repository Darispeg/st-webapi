package com.example.api_rest.service.ticket;

import com.example.api_rest.api.TicketRepository;
import com.example.api_rest.db.entities.Ticket;
import com.example.api_rest.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl extends GenericServiceImpl<Ticket, UUID> implements TicketService {

    @Autowired
    private TicketRepository _ticketRepository;

    @Override
    public CrudRepository<Ticket, UUID> getDao() {
        return _ticketRepository;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        ticket.setKey(UUID.randomUUID());
        ticket.setCreatedDate(LocalDateTime.now());
        return getDao().save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        ticket.setLastModifiedDate(LocalDateTime.now());
        return getDao().save(ticket);
    }

    @Override
    public List<Ticket> findByEventKey(UUID eventKey) {
        return _ticketRepository.findByEventKey(eventKey);
    }
}
