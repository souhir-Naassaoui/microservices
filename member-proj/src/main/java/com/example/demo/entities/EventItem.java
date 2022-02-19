package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public
class EventItem {
	@javax.persistence.Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long eventID;
	@Transient
	private Events event;
	@ManyToMany
	private Collection<AppUser> users=new ArrayList<AppUser>();
	public EventItem(Long id, Long eventID, Collection<AppUser> users) {
		super();
		Id = id;
		this.eventID = eventID;
		this.users = users;
	}
	
	 
}
