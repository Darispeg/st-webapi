package com.example.api_rest.mapping;

import lombok.Getter;

@Getter
public class UpdateUserMapping
{
    private String fullName;
    private String username;
    private String phone;
    private String address;
    private String email;
    private String status;
}
