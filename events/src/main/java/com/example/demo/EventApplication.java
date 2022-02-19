package com.example.demo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
class Event{
	@javax.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Date date;
	private String lieux;
}

interface EventRepository extends JpaRepository<Event, Long>{}
@CrossOrigin
@RestController
class EventController {
	
	@Autowired
	private EventRepository evenementRepository;
	
	@GetMapping("/events")
	private List<Event> getAll(){
		return evenementRepository.findAll();
	}
	@GetMapping("/events/{id}")
	private Optional<Event> getById(@PathVariable(name = "id") Long id){
		return evenementRepository.findById(id);
	}
	@PostMapping("/events/add")
	private Event add( @RequestBody Event e ) {
		return evenementRepository.save(e);
	}
	@PutMapping("/events/update/{id}")
	private Event update(@PathVariable(name = "id") Long id, @RequestBody Event e) {
		e.setId(id);
		return evenementRepository.save(e);
	}
	
	@DeleteMapping("/events/delete/{id}")
	private void delete(@PathVariable(name = "id") Long id) {
		evenementRepository.deleteById(id);
	}

}

@SpringBootApplication
public class EventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}
	@Bean
	CommandLineRunner start(EventRepository eventRepository) {
		return args ->{
			/*
			 * eventRepository.save(new Event(null, "event1", new Date(),"lieux1"));
			eventRepository.save(new Event(null, "event2", new Date(),"lieux2"));
			eventRepository.save(new Event(null, "event3", new Date(),"lieux3"));
			eventRepository.findAll().forEach(System.out::println);
			 */
		};
	}

}
