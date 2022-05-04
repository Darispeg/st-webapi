package com.example.api_rest.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("UserService")
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        com.example.api_rest.Model.User appUser =
                userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login Username Invalido"));


        Set grantList = new HashSet();

        UserDetails user = (UserDetails) new User(username, appUser.getPassword(), grantList);

        return user;
    }

}
