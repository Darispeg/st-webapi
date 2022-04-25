package com.example.api_rest.api;

import java.util.UUID;

import com.example.api_rest.Model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, UUID> {
    
}
