package com.example.demo.entities;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@DiscriminatorValue("ens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantChercheur extends AppUser {
	
	@NonNull
	private String grade,etablissement;
	@OneToMany(mappedBy = "encadrant")
	private Collection<Etudiant> etudiants;
	


}
