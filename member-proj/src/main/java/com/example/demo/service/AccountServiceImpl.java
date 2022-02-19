package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AppRoleRepository;
import com.example.demo.dao.AppUserRepository;
import com.example.demo.dao.EnseignantRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private EnseignantRepository enseignantChercheurRepository;
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
	public Etudiant saveEtudiant(String username, String password, String confirmedPassword) {
		Etudiant user=etudiantRepository.findByUsername(username);
		if(user!=null) throw new RuntimeException("User already exists");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
		Etudiant etudiant = new Etudiant();
		etudiant.setUsername(username);
		etudiant.setPassword(bCryptPasswordEncoder.encode(password));
		etudiant.setType("etd");
		etudiant.setPhoto("unknown.png");
		etudiantRepository.save(etudiant);
		addRoleToUser(username,"USER");
		return etudiant;
	}
	
	
	@Override
	public EnseignantChercheur saveEnseignantChercheur(String username, String password, String confirmedPassword) {
		EnseignantChercheur user=(EnseignantChercheur) enseignantChercheurRepository.findUserByUsername(username);
		if(user!=null) throw new RuntimeException("User already exists");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
		EnseignantChercheur etudiant = new EnseignantChercheur();
		etudiant.setUsername(username);
		etudiant.setPassword(bCryptPasswordEncoder.encode(password));
		etudiant.setType("ens");
		etudiant.setPhoto("unknown.png");
		enseignantChercheurRepository.save(etudiant);
		addRoleToUser(username,"USER");
		return etudiant;
	}
	
	/*
	 * @Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		AppUser user=appUserRepository.findUserByUsername(username);
		if(user!=null) throw new RuntimeException("User already exists");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));

		appUserRepository.save(appUser);
		addRoleToUser(username,"USER");
		return appUser;
	}
	 */

	@Override
	public AppRole saveRole(AppRole role) {
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

	
	
	

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser=appUserRepository.findByUsername(username);
		AppRole appRole=appRoleRepository.findByRoleName(roleName);
		appUser.getRoles().add(appRole);
	}


	

}
