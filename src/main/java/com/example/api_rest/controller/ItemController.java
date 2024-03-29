package com.example.api_rest.controller;

import com.example.api_rest.db.entities.Item;
import com.example.api_rest.mapping.UpdateItemMapping;
import com.example.api_rest.model.ResponseMessage;
import com.example.api_rest.service.item.ItemService;
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
@RequestMapping(value = "/api/v1/items")
public class ItemController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private ItemService _itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAll()
    {
        return ResponseEntity.ok(_itemService.findAll());
    }

    @GetMapping(value = "/{key}")
    public ResponseEntity<Item> getEvent(@PathVariable UUID key)
    {
        return ResponseEntity.ok(_itemService.findById(key));
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Item>> getAllBYCategory(@RequestParam String category)
    {
        if (category.isEmpty())
            return ResponseEntity.ok(_itemService.findAll());
        else
            return ResponseEntity.ok(_itemService.findAllByCategory(category));
    }


    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/items/save").toUriString());
        return ResponseEntity.created(uri).body(_itemService.saveEvent(item));
    }

    @PutMapping(value = "/{key}")
    public ResponseEntity<Item> update(@PathVariable UUID key, @RequestBody UpdateItemMapping updateObj)
    {
        Optional<Item> obj = Optional.ofNullable(_itemService.findById(key));

        if (obj.isPresent())
        {
            Item update = obj.get();
            update.setName(updateObj.getName());
            update.setDescription(updateObj.getDescription());
            update.setCategory(updateObj.getCategory());
            update.setPrice(updateObj.getPrice());
            update.setStock(updateObj.getStock());
            update.setUnitOfMeasurement(updateObj.getUnitOfMeasurement());

            Item modified = _itemService.update(update);
            return ResponseEntity.ok(modified);
        }
        else
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{key}")
    public ResponseEntity<String> delete(@PathVariable UUID key)
    {
        Optional<Item> obj = Optional.ofNullable(_itemService.findById(key));

        if (obj.isPresent())
        {
            _itemService.delete(key);
            return new ResponseEntity<>("Item deleted successfully", HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>( "Item not found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{itemKey}/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable UUID itemKey, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String fileKey = storageService.save(file);

            Optional<Item> event = Optional.ofNullable(_itemService.findById(itemKey));

            if (event.isPresent())
            {
                String path = "https://st-webapi-production.up.railway.app/api/v1/files/" + fileKey;
                event.get().setUrlImage(path);
                Item modified = _itemService.update(event.get());
            }

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/{itemKey}/upload-model-ar")
    public ResponseEntity<ResponseMessage> uploadModel(@PathVariable UUID itemKey, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String fileKey = storageService.save(file);

            Optional<Item> event = Optional.ofNullable(_itemService.findById(itemKey));

            if (event.isPresent())
            {
                String path = "https://st-webapi-production.up.railway.app/api/v1/files/" + fileKey;
                event.get().setUrlModelAR(path);
                Item modified = _itemService.update(event.get());
            }

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
