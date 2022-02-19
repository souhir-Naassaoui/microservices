package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.dao.EnseignantRepository;
import com.example.demo.dao.EventItemRepository;
import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.EventItem;
import com.example.demo.entities.Events;

@RestController
@CrossOrigin
public class EnseignantRestService {

	@Autowired
	private EnseignantRepository enseignatRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AppUserRepository appUserRepository;

	@GetMapping("/enseignants")
	public List<EnseignantChercheur> getContacts() {
		return enseignatRepository.findAll();
	}

	@GetMapping("/enseignants/{id}")
	public Optional<EnseignantChercheur> getContact(@PathVariable Long id) {
		return enseignatRepository.findById(id);
	}

	@GetMapping(value = "enseignants/adel")
	@ResponseBody
	public EnseignantChercheur findOneByUsername(@RequestParam("username") final String username) {
		return enseignatRepository.findUserByUsername(username);
	}

	@DeleteMapping("/enseignants/{id}")
	public void deleteContact(@PathVariable Long id) {
		enseignatRepository.deleteById(id);
	}

	@PostMapping("/registerEns")
	public AppUser register(@RequestBody UserForm userForm) {
		return accountService.saveEnseignantChercheur(userForm.getUsername(), userForm.getPassword(),
				userForm.getConfirmedPassword());
	}
	@PostMapping(path = "/enseignants/uploadPhoto/{id}")
	public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
		EnseignantChercheur p =enseignatRepository.findById(id).get();
		p.setPhoto(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/frontPhotos/products/"+p.getPhoto()), file.getBytes());
		enseignatRepository.save(p);
	}

	@PutMapping("/enseignants/{id}")
	public EnseignantChercheur updateContact(@PathVariable Long id, @RequestBody EnseignantChercheur contact) {
		contact.setId(id);
		AppUser appUser = appUserRepository.findByUsername(contact.getUsername());
		Collection<AppRole> roles = appUser.getRoles();
		contact.setRoles(roles);
		return enseignatRepository.save(contact);
	}

	@GetMapping("/enseignants/addRole")
	public void addRole(@RequestParam String username) {
		accountService.addRoleToUser(username, "ADMIN");
	}
	/*
	 * @GetMapping("/chercherEnseignants") public Page<EnseignantChercheur>
	 * chercher( @RequestParam(name = "mc", defaultValue = "") String mc,
	 * 
	 * @RequestParam(name = "page", defaultValue = "0") int page,
	 * 
	 * @RequestParam(name = "size", defaultValue = "5") int size){ return
	 * enseignatRepository.chercher("%"+mc+"%", PageRequest.of(page, size)); }
	 */

	@GetMapping(path = "/enseignants/photoEnseignant/{id}", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		EnseignantChercheur e = enseignatRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/frontPhotos/products/" + e.getPhoto()));
	}
	@GetMapping(path = "/enseignants/cvEnseignant/{id}", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public byte[] getCV(@PathVariable("id") Long id) throws Exception {
		EnseignantChercheur e = enseignatRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/frontPhotos/products/" + e.getCv()));
	}

	@PostMapping(path = "/enseignants/uploadCV/{id}/files")
	public void uploadCV(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws Exception {
		EnseignantChercheur p = enseignatRepository.findById(id).get();

		System.out.println("Deletion successful.");
		try {

			Files.deleteIfExists(Paths.get(System.getProperty("user.home") + "/frontPhotos/products/" + p.getCv()));
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Files.deleteIfExists(Paths.get(System.getProperty("user.home")+"/frontPhotos/products/"+p.getCv()));
		Path root = Paths.get(System.getProperty("user.home") + "/frontPhotos/products/");
		Path resolve = root.resolve(id + "cv.pdf");
		// Files.deleteIfExists(resolve);

		p.setCv(id + "cv.pdf");
		Files.copy(file.getInputStream(), resolve);
		enseignatRepository.save(p);
	}
	@Autowired
	private EventItemRepository eventItemRepository;
	@Autowired
	private EventService eventService;
	
	@GetMapping("/enseignants/event/{id}/{iduser}")
	public void addEvent(@PathVariable Long id,@PathVariable Long iduser) {
		Events e=eventService.findEventById(id);
		EventItem e1=null;
		try {
			e1=eventItemRepository.findById(id).get();
			EnseignantChercheur p = enseignatRepository.findById(iduser).get();
			e1.setEventID(e.getId());
			e1.setEvent(e);
			e1.getUsers().add(p);
			eventItemRepository.save(e1);
		}catch(NoSuchElementException ee) {
			e1=eventItemRepository.save(new EventItem(id,id, null));
			EnseignantChercheur p = enseignatRepository.findById(iduser).get();
			e1.setEventID(e.getId());
			e1.setEvent(e);
			e1.getUsers().add(p);
			eventItemRepository.save(e1);
		}
		
		/*
		 * if(e1==null) {
			eventItemRepository.save(new EventItem(id,id, null));
		}
		EnseignantChercheur p = enseignatRepository.findById(iduser).get();
		e1.setEventID(e.getId());
		e1.setEvent(e);
		e1.getUsers().add(p);

		EventItem e11=eventItemRepository.save(e1);
		 */
		
	}

}
