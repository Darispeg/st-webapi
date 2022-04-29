package com.example.api_rest.db.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter @Setter @AllArgsConstructor
@Document
public class Role {
    
    @Id
    @Field("_id")
    private UUID key;

    @Field
    private String name;

    @Field
    private String description;

}
