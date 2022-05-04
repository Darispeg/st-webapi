package com.example.api_rest;

import com.example.api_rest.db.entities.Role;
import com.example.api_rest.db.entities.User;
import com.example.api_rest.service.role.RoleService;
import com.example.api_rest.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "12345";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println();
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + encodedPassword);
	}

	/*@Bean
	CommandLineRunner run(UserService userService, RoleService roleService)
	{
		return args -> {
			User user = new User("Rick Sanchez", "Rick", "rick@gmail.com", "12345", "79752455", "Juliana Arias");
			List roles = new ArrayList<>();
			roles.add(new Role(null, "ROLE_USER", "usuario"));
			roles.add(new Role(null, "ROLE_ADMIN", "administrador"));
			user.setRoles(roles);
			userService.saveUser(user);
		};
	}*/
}
