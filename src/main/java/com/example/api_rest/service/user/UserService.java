package com.example.api_rest.service.user;

import com.example.api_rest.Model.User;
import com.example.api_rest.service.GenericService;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends GenericService<User, UUID> {
    Optional<User> findByUsername(String username);
    User saveAdministrator(User obj);
    User update(User obj);
}
