package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entities.Category;


@RestController
@CrossOrigin
public class CategoryRestService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/categories")
	public List<Category> getCategories(){
		return categoryRepository.findAll();
	}
	
	@GetMapping("/categories/{id}")
	public Optional<Category> getCategory(@PathVariable Long id) {
		return categoryRepository.findById(id);
	}
	
	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable Long id) {
		categoryRepository.deleteById(id);
	}
	
	@PostMapping("/categories")
	public Category addCategory(@RequestBody Category category){
		return categoryRepository.save(category);
	}
	
	@PutMapping("/categories/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
		category.setId(id);
		return categoryRepository.save(category);
	}
	

}
