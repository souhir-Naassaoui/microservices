package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Evenement;
import com.example.demo.repositorey.EvenementRepository;

@RestController
public class EventController {
	
	@Autowired
	private EvenementRepository evenementRepository;
	
	@GetMapping("/events")
	private List<Evenement> getAll(){
		return evenementRepository.findAll();
	}

}
