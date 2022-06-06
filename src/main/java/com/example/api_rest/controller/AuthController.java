package com.example.api_rest.controller;

import com.example.api_rest.api.UserRepository;
import com.example.api_rest.configurations.jwt.TokenProvider;
import com.example.api_rest.db.entities.User;
import com.example.api_rest.model.AuthToken;
import com.example.api_rest.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenProvider jwtTokenUtil;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping(value="/register")
    public User saveUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/adminping")
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value="/userping")
    public String userPing(){
        return "Any User Can Read This";
    }
}
