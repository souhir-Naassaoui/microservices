package com.example.demo.repositorey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.Evenement;
@RepositoryRestResource
public interface EvenementRepository extends JpaRepository<Evenement, Long>{

}
