package com.example.api_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
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
