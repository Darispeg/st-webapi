package com.example.api_rest;

import com.example.api_rest.service.storage.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ApiRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

	@Resource
	FileStorageService storageService;
	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll();
		//storageService.init();
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
