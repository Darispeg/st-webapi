package com.example.api_rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CardRequest {
    private String number;
    private int exp_month;
    private int exp_year;
    private String cvc;
}
