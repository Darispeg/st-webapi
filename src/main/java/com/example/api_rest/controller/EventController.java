package com.example.api_rest.controller;

import com.example.api_rest.db.entities.Item;
import com.example.api_rest.mapping.UpdateItemMapping;
import com.example.api_rest.model.ResponseMessage;
import com.example.api_rest.service.event.EventService;
import com.example.api_rest.service.storage.FileStorageService;
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

    @GetMapping
    public ResponseEntity<List<Item>> getAll()
    {
        return ResponseEntity.ok(_eventService.findAll());
    }

    @GetMapping(value = "/{key}")
    public ResponseEntity<Item> getEvent(@PathVariable UUID key)
    {
        return ResponseEntity.ok(_eventService.findById(key));
    }

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/events/save").toUriString());
        return ResponseEntity.created(uri).body(_eventService.saveEvent(item));
    }

    @PutMapping(value = "/{key}")
    public ResponseEntity<Item> update(@PathVariable UUID key, @RequestBody UpdateItemMapping updateObj)
    {
        Optional<Item> obj = Optional.ofNullable(_eventService.findById(key));

        if (obj.isPresent())
        {
            Item update = obj.get();
            update.setName(updateObj.getName());
            update.setDescription(updateObj.getDescription());
            update.setCategory(updateObj.getCategory());
            update.setPrice(updateObj.getPrice());
            update.setStock(updateObj.getStock());
            update.setUnitOfMeasurement(updateObj.getUnitOfMeasurement());

            Item modified = _eventService.update(update);
            return ResponseEntity.ok(modified);
        }
        else
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{key}")
    public ResponseEntity<String> delete(@PathVariable UUID key)
    {
        Optional<Item> obj = Optional.ofNullable(_eventService.findById(key));

        if (obj.isPresent())
        {
            _eventService.delete(key);
            return new ResponseEntity<>("Event deleted successfully", HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>( "Event not found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{eventKey}/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable UUID eventKey, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String fileKey = storageService.save(file);

            Optional<Item> event = Optional.ofNullable(_eventService.findById(eventKey));

            if (event.isPresent())
            {
                URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/events/files/" + fileKey).toUriString());
                event.get().setUrlImage(uri.toString());
                Item modified = _eventService.update(event.get());
            }

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
