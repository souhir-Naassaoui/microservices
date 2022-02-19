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
class Product{
	@javax.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String name;
	private double price;
}
@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product, Long>{}

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ProductRepository  productRepository) {
		return args ->{
			productRepository.save(new Product(null, "Computer Asus", 2200));
			productRepository.save(new Product(null, "Printer Epson 220", 1500));
			productRepository.save(new Product(null, "Computer Dell", 3000));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
