package com.example.api_rest;

import com.example.api_rest.api.UserRepository;
import com.example.api_rest.db.entities.User;
import com.example.api_rest.service.storage.FileStorageService;
import com.example.api_rest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner run(UserRepository repository)
	{
		return args -> {
			User user = repository.findByUsername("Rick");
			System.out.println(passwordEncoder.matches("12345", user.getPassword()));
		};
	}
}
