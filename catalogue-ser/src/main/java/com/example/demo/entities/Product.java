package com.example.demo.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
	@org.springframework.data.annotation.Id
	private String id;
	private String name;
	private double price;
	@DBRef
	private Category category;

}