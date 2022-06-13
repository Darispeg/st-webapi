package com.example.api_rest.controller;

import com.example.api_rest.db.entities.Event;
import com.example.api_rest.db.entities.Ticket;
import com.example.api_rest.mapping.UpdateEventMapping;
import com.example.api_rest.model.ResponseMessage;
import com.example.api_rest.service.event.EventService;
import com.example.api_rest.service.storage.FileStorageService;
import com.example.api_rest.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/events")
public class EventController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private EventService _eventService;

    @Autowired
    private TicketService _ticketService;

    @GetMapping
    public ResponseEntity<List<Event>> getAll()
    {
        return ResponseEntity.ok(_eventService.findAll());
    }

    @GetMapping(value = "/{key}")
    public ResponseEntity<Event> getEvent(@PathVariable UUID key)
    {
        return ResponseEntity.ok(_eventService.findById(key));
    }

    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/events/save").toUriString());
        return ResponseEntity.created(uri).body(_eventService.saveEvent(event));
    }

    @PutMapping(value = "/{key}")
    public ResponseEntity<Event> update(@PathVariable UUID key, @RequestBody UpdateEventMapping updateObj)
    {
        Optional<Event> obj = Optional.ofNullable(_eventService.findById(key));

        if (obj.isPresent())
        {
            obj.get().setName(updateObj.getName());
            obj.get().setDescription(updateObj.getDescription());
            obj.get().setPhone(updateObj.getPhone());
            obj.get().setAddress(updateObj.getAddress());
            obj.get().setCity(updateObj.getCity());
            obj.get().setCategory(updateObj.getCategory());
            obj.get().setStatus(updateObj.getStatus());
            obj.get().setDateTime(updateObj.getDateTime());
            obj.get().setLocation(updateObj.getLocation());

            Event modified = _eventService.update(obj.get());
            return ResponseEntity.ok(modified);
        }
        else
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{key}")
    public ResponseEntity<String> delete(@PathVariable UUID key)
    {
        Optional<Event> obj = Optional.ofNullable(_eventService.findById(key));

        if (obj.isPresent())
        {
            _eventService.delete(key);
            return new ResponseEntity<>("Event deleted successfully", HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>( "Event not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "{eventKey}/tickets")
    public ResponseEntity<List<Ticket>> listEventTicket(@PathVariable UUID eventKey)
    {
        List<Ticket> listTickets = _ticketService.findByEventKey(eventKey);
        if (!listTickets.isEmpty())
            return ResponseEntity.ok(listTickets);
        else
            return new ResponseEntity<>( null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "{eventKey}/tickets")
    public ResponseEntity<Ticket> saveEventTicket(@PathVariable UUID eventKey, @RequestBody Ticket ticket)
    {
        Event event = _eventService.findById(eventKey);
        if (event != null)
        {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/events/" + eventKey + "/tickets/save").toUriString());
            return ResponseEntity.created(uri).body(_ticketService.saveTicket(ticket));
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{eventKey}/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable UUID eventKey, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String fileKey = storageService.save(file);

            Optional<Event> event = Optional.ofNullable(_eventService.findById(eventKey));

            if (event.isPresent())
            {
                URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/events/files/" + fileKey).toUriString());
                event.get().setUrlImage(uri.toString());
                Event modified = _eventService.update(event.get());
            }

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
