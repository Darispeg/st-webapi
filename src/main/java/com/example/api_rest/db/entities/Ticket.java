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
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Ticket {

    @Id
    @Field("_id")
    private UUID key;

    @Field
    private String type;

    @Field
    private double price;

    @Field
    private int available;

    @Field
    private LocalDateTime  startDate;

    @Field
    private LocalDateTime finishDate;

    @Field
    private List<AdditionalInformation> additionalInformation;

    @Field
    private UUID eventKey;

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
