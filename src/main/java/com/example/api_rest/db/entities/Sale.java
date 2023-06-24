package com.example.api_rest.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sales")
public class Sale {

    @Id
    @Field("_id")
    private UUID key;

    @Field
    private String email;

    @Field
    private String phone;

    @Field
    private String name;

    @Field
    private String details;

    @Field
    private int total;

    @Field
    private String chargeId;

    @Field
    private LocalDateTime createdDate;
}
