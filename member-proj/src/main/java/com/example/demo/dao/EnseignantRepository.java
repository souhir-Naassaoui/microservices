package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.entities.AppUser;
import com.example.demo.entities.EnseignantChercheur;
@RepositoryRestResource
public interface EnseignantRepository extends JpaRepository<EnseignantChercheur, Long>{
	
	EnseignantChercheur findUserByUsername(String username);
	
	@RestResource(path = "/getEnseignant")
	EnseignantChercheur findByUsername(@Param("username") String username);

	//Page<EnseignantChercheur> chercher(String string, PageRequest of);

}
