package com.example.api_rest.controller;

import com.example.api_rest.api.UserRepository;
import com.example.api_rest.model.AuthenticationRequest;
import com.example.api_rest.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest)
    {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception ex){
            return new ResponseEntity<>("Error during client Authentication " + username, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new AuthenticationResponse("Successful Authentication for client: " + username));
    }

    @GetMapping("/index")
    public String index()
    {
        return "Welcome to the Ticket System";
    }

}
