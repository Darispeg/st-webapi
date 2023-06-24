package com.example.api_rest.security;

import com.example.api_rest.api.UserRepository;
import com.example.api_rest.db.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository
                .findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("the user with username " + username + " don't exist"));
        return new UserDetailImpl(userEntity);
    }
}
