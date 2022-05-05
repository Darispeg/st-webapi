package com.example.api_rest.mapping;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateEventMapping {
    private String name;
    private String category;
    private String description;
    private String phone;
    private String address;
    private String city;
    private String location;
    private LocalDateTime dateTime;
    private String status;
}
