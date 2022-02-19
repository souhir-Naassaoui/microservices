package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Events;

@FeignClient(name = "EVENT-SERVICE")
public interface EventService {
	@GetMapping("/events/{id}")
	public Events findEventById(@PathVariable(name = "id") Long id);
}