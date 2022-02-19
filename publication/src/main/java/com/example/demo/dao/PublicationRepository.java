package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Publication;

@RepositoryRestResource
public interface PublicationRepository extends JpaRepository<Publication, Long>{
	
	List<Publication> findByAuteurId(Long auteurId);

}
