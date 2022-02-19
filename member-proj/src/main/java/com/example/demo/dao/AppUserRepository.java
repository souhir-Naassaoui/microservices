package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.entities.AppUser;
@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	@RestResource(path = "/getUser")
	AppUser findByUsername(@Param("username") String username);

}
