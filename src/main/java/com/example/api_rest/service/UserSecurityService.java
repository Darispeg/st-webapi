package com.example.api_rest.service;

import com.example.api_rest.api.UserRepository;
import com.example.api_rest.db.entities.Role;
import com.example.api_rest.db.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundedByUsername = userRepository.findByUsername(username);
        if (foundedByUsername == null)
            return null;

        String name = foundedByUsername.getUsername();
        String pwd = foundedByUsername.getPassword();
        Set grantList = new HashSet();
        grantList.add(foundedByUsername.getRole().getDescription());

        return new org.springframework.security.core.userdetails.User(name, pwd, grantList);
    }
}
