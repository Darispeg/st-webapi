package com.example.api_rest.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Document
public class User {

    @Id
    @Field("_id")
    private UUID key;

    @Field
    private String fullName;

    @Field
    private String username;

    @Field
    private String email;

    @Field
    private String password;

    @Transient
    private String confirmPassword;

    @Field
    private String phone;

    @Field
    private String address;

    @Field
    @CreatedBy
    private String createdBy;

    @Field
    private LocalDateTime createdDate;

    @Field
    private LocalDateTime lastModifiedDate;

    @Field
    private Collection<Role> roles;

    @Field
    private String status;

    public User(String fullName, String username, String email, String password, String phone, String address)
    {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User orElseThrow(Object object) {
        return null;
    }
}
