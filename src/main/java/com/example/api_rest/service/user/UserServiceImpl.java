package com.example.api_rest.service.user;

import com.example.api_rest.Model.User;
import com.example.api_rest.api.UserRepository;
import com.example.api_rest.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("UserService")
public class UserServiceImpl extends GenericServiceImpl<User, UUID> implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CrudRepository<User, UUID> getDao() {
        return repository;
    }

    @Override
    public User saveAdministrator(User obj) {
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

        for (User administrator : listUsers) {
            if(administrator.getUsername().equals(username))
            {
                return Optional.of(administrator);
            }
        }
        return Optional.empty();
    }
}
