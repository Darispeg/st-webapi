package com.example.api_rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentRequest {
    private String email;
    private String phone;
    private String name;
    private String details;
    private int total;
    private CardRequest cardRequest;
}
