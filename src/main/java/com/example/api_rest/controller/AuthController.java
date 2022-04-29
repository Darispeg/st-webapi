package com.example.api_rest.controller;

import com.example.api_rest.api.UserRepository;
import com.example.api_rest.model.AuthenticationRequest;
import com.example.api_rest.model.AuthenticationResponse;
import com.example.api_rest.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUntil;


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

        return ResponseEntity.ok(new AuthenticationResponse("Successful Authentication for client: " + jwtUntil.generateToken(username)));
    }

    @GetMapping("/index")
    public String index()
    {
        return "Welcome to the Ticket System";
    }

}
