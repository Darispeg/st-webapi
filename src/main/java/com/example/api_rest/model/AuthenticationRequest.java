package com.example.api_rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationRequest {
    private String username;
    private String password;
}
