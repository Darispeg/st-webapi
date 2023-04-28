package com.example.api_rest.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Item {

    @Id
    @Field("_id")
    private UUID key;

    @Field
    private String name;

    @Field
    private String category;

    @Field
    private String description;

    @Field
    private float price;

    @Field
    private String unitOfMeasurement;

    @Field
    private int stock;

    @Field
    private String status;

    @Field
    private String urlImage;

    //region Audit

    @Field
    @CreatedBy
    private String createdBy;

    @Field
    private LocalDateTime createdDate;

    @Field
    private LocalDateTime lastModifiedDate;

    //endregion

}
