package com.example.api_rest.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "categories")
public class Category {
    @Id
    @Field("_id")
    private UUID key;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private String color;
}
