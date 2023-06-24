package com.example.api_rest.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;
    private String password;
}
