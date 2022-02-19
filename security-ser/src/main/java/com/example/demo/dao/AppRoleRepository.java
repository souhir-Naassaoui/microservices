package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	
	AppRole findUserByRoleName(String roleName);

}
