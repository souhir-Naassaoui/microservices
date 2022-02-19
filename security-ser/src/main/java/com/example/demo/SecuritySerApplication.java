package com.example.demo;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.AppRole;
import com.example.demo.service.AccountService;

@SpringBootApplication
public class SecuritySerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args ->{
			accountService.saveRole(new AppRole(null,"USER"));
			accountService.saveRole(new AppRole(null,"ADMIN"));
			Stream.of("user1","user2","user3","admin").forEach(un->{
				accountService.saveUser(un, "1234", "1234");
			});
			accountService.addRoleToUser("admin", "ADMIN");
		};
	}
	
	@Bean
	BCryptPasswordEncoder getPCPE() {
		return new BCryptPasswordEncoder();
	}

}
