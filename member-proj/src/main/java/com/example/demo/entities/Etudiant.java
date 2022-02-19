package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@DiscriminatorValue("etd")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant extends AppUser {
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date dateInscription;

	private String diplome;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private EnseignantChercheur encadrant;

	

}
