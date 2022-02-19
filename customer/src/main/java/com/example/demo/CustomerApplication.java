package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
class Customer{
	@javax.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String name,email;
}
@RepositoryRestResource
interface CustomerRepository extends JpaRepository<Customer, Long>{}

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args ->{
			customerRepository.save(new Customer(null, "souhir", "souhir@gmail.com"));
			customerRepository.save(new Customer(null, "houyem", "houyem@gmail.com"));
			customerRepository.save(new Customer(null, "tasnim", "tasnim@gmail.com"));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
