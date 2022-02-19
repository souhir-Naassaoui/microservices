package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dao.EventItemRepository;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.EventItem;
import com.example.demo.entities.Events;
import com.example.demo.service.AccountService;
import com.example.demo.service.EnseignantRestService;
import com.example.demo.service.EventService;




@SpringBootApplication
@EnableFeignClients
public class SecuritySerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(AccountService accountService,EventService eventService,EventItemRepository eventItemRepository,
			EnseignantRestService enseignantRestService) {
		return args ->{
			/*
			 *  accountService.saveRole(new AppRole(null,"USER"));
			accountService.saveRole(new AppRole(null,"ADMIN"));
			
			accountService.saveEnseignantChercheur("admin","123","123");
			accountService.addRoleToUser("admin", "ADMIN");
			Events e=eventService.findEventById(2L);
			//System.out.println(e.getTitle());
			EnseignantChercheur u=(EnseignantChercheur) accountService.loadUserByUsername("slim");
			EnseignantChercheur u2=(EnseignantChercheur) accountService.loadUserByUsername("admin");
			//System.out.println(u.getId());
			EventItem ei=new EventItem();
			ei.setEventID(e.getId());
			ei.setEvent(e);
			ei.getUsers().add(u);
			ei.getUsers().add(u2);
			EventItem ei1=eventItemRepository.save(ei);
			 */
			
			
			 
		};
	}
	
	@Bean
	BCryptPasswordEncoder getPCPE() {
		return new BCryptPasswordEncoder();
	}
	

}
