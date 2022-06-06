package com.example.api_rest.service.user;

import com.example.api_rest.api.RoleRepository;
import com.example.api_rest.db.entities.Role;
import com.example.api_rest.db.entities.User;
import com.example.api_rest.api.UserRepository;
import com.example.api_rest.service.GenericServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("UserService") @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl extends GenericServiceImpl<User, UUID> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CrudRepository<User, UUID> getDao() {
        return userRepository;
    }

    @Override
    public User saveUser(User obj) {
        obj.setKey(UUID.randomUUID());

        String encodePassword = bCryptPasswordEncoder.encode(obj.getPassword());
        obj.setPassword(encodePassword);

        obj.setCreatedDate(LocalDateTime.now());
        return getDao().save(obj);
    }

    @Override
    public User update(User obj) {
        obj.setLastModifiedDate(LocalDateTime.now());
        return getDao().save(obj);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> listUsers = new ArrayList<>();
        getDao().findAll().forEach(obj -> listUsers.add(obj));

        for (User user : listUsers) {
            if(user.getUsername().equals(username))
            {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addRoleToUser(UUID userId, UUID roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        user.get().setRole(role.get());
        userRepository.save(user.get());
    }
}
