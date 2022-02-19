package com.example.demo.service;

import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;

public interface AccountService {
	
	//AppUser saveUser(String username,String password,String confirmedPassword);
	AppRole saveRole(AppRole role );
	AppUser loadUserByUsername(String username);
	void addRoleToUser(String username,String roleName);
	Etudiant saveEtudiant(String username, String password, String confirmedPassword);
	EnseignantChercheur saveEnseignantChercheur(String username, String password, String confirmedPassword);
	//Etudiant loadEtudiantByUsername(String username);
	//EnseignantChercheur loadEnseignantChercheurByUsername(String username);

}
