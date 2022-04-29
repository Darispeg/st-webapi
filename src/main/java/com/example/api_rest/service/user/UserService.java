package com.example.api_rest.service.user;

import com.example.api_rest.db.entities.User;
import com.example.api_rest.service.GenericService;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends GenericService<User, UUID> {
    Optional<User> findByUsername(String username);
    User saveUser(User obj);
    User update(User obj);
    void addRoleToUser(UUID userId, UUID roleId);
}
