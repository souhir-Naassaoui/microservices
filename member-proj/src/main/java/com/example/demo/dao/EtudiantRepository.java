package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.AppUser;
import com.example.demo.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

	@Query("select c from Etudiant c where c.nom like :x ")
	public Page<Etudiant> chercher(@Param("x") String mc, Pageable pageable);

	List<Etudiant> findByDiplome(String diplome);

	List<Etudiant> findByDiplomeOrderByDateInscriptionDesc(String diplome);
	
	Etudiant findByUsername(String username);

	//public Page<Etudiant> chercher(String mc, PageRequest of);

}

