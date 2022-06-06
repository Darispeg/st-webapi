package com.example.api_rest.controller;

import com.example.api_rest.db.entities.User;
import com.example.api_rest.mapping.AddRoleUserMapping;
import com.example.api_rest.mapping.UpdateUserMapping;
import com.example.api_rest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService _userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll()
    {
        return ResponseEntity.ok(_userService.findAll());
    }

    @GetMapping(value = "/{key}")
    public ResponseEntity<User> getUser(@PathVariable UUID key)
    {
        return ResponseEntity.ok(_userService.findById(key));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/events/save").toUriString());
        return ResponseEntity.created(uri).body(_userService.saveUser(user));
    }

    @PutMapping("/role")
    public ResponseEntity<String> addRoleToUser(@RequestBody AddRoleUserMapping addRoleToUser)
    {
        _userService.addRoleToUser(addRoleToUser.getUserKey(), addRoleToUser.getRoleKey());
        return new ResponseEntity<>("Change Role to User successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/{key}")
    public ResponseEntity<User> update(@PathVariable UUID key, @RequestBody UpdateUserMapping updateObj)
    {
        Optional<User> obj = Optional.ofNullable(_userService.findById(key));

        if (obj.isPresent())
        {
            obj.get().setFullname(updateObj.getFullname());
            obj.get().setPhone(updateObj.getPhone());
            obj.get().setAddress(updateObj.getAddress());
            obj.get().setEmail(updateObj.getEmail());
            obj.get().setFullname(updateObj.getFullname());
            obj.get().setStatus(updateObj.getStatus());

            User modified = _userService.update(obj.get());
            return ResponseEntity.ok(modified);
        }
        else
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{key}")
    public ResponseEntity<String> delete(@PathVariable UUID key)
    {
        Optional<User> obj = Optional.ofNullable(_userService.findById(key));

        if (obj.isPresent())
        {
            _userService.delete(key);
            return new ResponseEntity<>("Event deleted successfully", HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>( "Event not found", HttpStatus.BAD_REQUEST);
    }
}
