package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.PublicationRepository;
import com.example.demo.entities.Category;
import com.example.demo.entities.Publication;

@SpringBootApplication
public class PublicationSerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicationSerApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CategoryRepository categoryRepository,PublicationRepository pubRepository) {
		return args->{
			
			
			//Category c1=categoryRepository.save(new Category(null,"Data science",new ArrayList<>()));
			//Category c2=categoryRepository.save(new Category(null,"Blockchain",new ArrayList<>()));
			//categoryRepository.findAll().forEach(System.out::println);
			
		};
	}
}
