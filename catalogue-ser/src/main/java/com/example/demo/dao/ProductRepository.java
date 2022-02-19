package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends MongoRepository<Product, String>{

}
