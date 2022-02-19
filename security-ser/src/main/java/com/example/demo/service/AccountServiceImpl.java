package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AppRoleRepository;
import com.example.demo.dao.AppUserRepository;
import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	

	public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		AppUser user=appUserRepository.findUserByUsername(username);
		if(user!=null) throw new RuntimeException("User already exists");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUser.setActived(true);
		appUserRepository.save(appUser);
		addRoleToUser(username,"USER");
		return appUser;
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findUserByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser=appUserRepository.findUserByUsername(username);
		AppRole appRole=appRoleRepository.findUserByRoleName(roleName);
		appUser.getRoles().add(appRole);

	}

}
