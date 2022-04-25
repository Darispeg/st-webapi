package com.example.api_rest.Model;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class User {

    @Id
    @Field("_id")
    private UUID key;

    @Field
    private String fullname;

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
    private String[] roles;

    @Field
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User() {}

    public User(String fullname, String username, String email, String password, String phone, String address) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key){
        this.key = key;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedDate(LocalDateTime dateTime)
    {
        this.createdDate = dateTime;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setLastModifiedDate(LocalDateTime dateTime)
    {
        this.lastModifiedDate = dateTime;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setName(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User orElseThrow(Object object) {
        return null;
    }
}
