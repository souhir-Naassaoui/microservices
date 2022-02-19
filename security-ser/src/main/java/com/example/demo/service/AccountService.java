package com.example.demo.service;

import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;

public interface AccountService {
	
	AppUser saveUser(String username,String password,String confirmedPassword);
	AppRole saveRole(AppRole role );
	AppUser loadUserByUsername(String username);
	void addRoleToUser(String username,String roleName);

}
