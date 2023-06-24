package com.example.api_rest.api;

import com.example.api_rest.db.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository(value = "userRepository")
public interface UserRepository extends MongoRepository<User, UUID> {
    User findByUsername(String username);
    Optional<User> findOneByUsername(String username);
}
