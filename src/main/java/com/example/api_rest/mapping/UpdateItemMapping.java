package com.example.api_rest.mapping;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateItemMapping {
    private String name;
    private String category;
    private String description;
    private String unitOfMeasurement;
    private float price;
    private int stock;
}
