package com.example.api_rest.controller;

import com.example.api_rest.Model.User;
import com.example.api_rest.mapping.UserUpdate;
import com.example.api_rest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService _administratorService;

    
    @GetMapping(value = {"/","/login"})
    public String index(){
        return "Por favor inicie su sesion";
    }

    @GetMapping
    public List<User> getAll()
    {
        return _administratorService.findAll();
    }

    @GetMapping(value = "/{key}")
    public User getAdministrator(@PathVariable UUID key)
    {
        return _administratorService.findById(key);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User administrator)
    {
        User obj = _administratorService.saveAdministrator(administrator);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping(value = "/{key}")
    public ResponseEntity<User> update(@PathVariable UUID key, @RequestBody UserUpdate updateObj)
    {
        Optional<User> obj = Optional.ofNullable(_administratorService.findById(key));

        if (obj.isPresent())
        {
            obj.get().setFullname(updateObj.getFullname());
            obj.get().setPhone(updateObj.getPhone());
            obj.get().setAddress(updateObj.getAddress());
            obj.get().setEmail(updateObj.getEmail());
            obj.get().setFullname(updateObj.getFullname());
            obj.get().setStatus(updateObj.getStatus());

            User modified = _administratorService.update(obj.get());
            return new ResponseEntity<>(modified, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{key}")
    public ResponseEntity<String> delete(@PathVariable UUID key)
    {
        Optional<User> obj = Optional.ofNullable(_administratorService.findById(key));

        if (obj.isPresent())
        {
            _administratorService.delete(key);
            return new ResponseEntity<>("Usuario Eliminado Correctamente", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>( "Usuario no encontrado", HttpStatus.BAD_REQUEST);
    }
}
