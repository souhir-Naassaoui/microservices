package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_mbr", discriminatorType = DiscriminatorType.STRING, length = 3)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String username;
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@NonNull
	private String cin, nom, prenom;
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(unique = true)
	private String email;
	private long tel;
	private String photo="unknown.png";
	
	private String cv;
	@ManyToMany(fetch = FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)	
	private Collection<AppRole> roles = new ArrayList<>();
	private String type;
	

	
}
