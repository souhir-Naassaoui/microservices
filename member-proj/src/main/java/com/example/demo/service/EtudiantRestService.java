package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.dao.EnseignantRepository;
import com.example.demo.dao.EtudiantRepository;
import com.example.demo.entities.AppRole;
import com.example.demo.entities.AppUser;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;

import lombok.Data;


@RestController
@CrossOrigin
public class EtudiantRestService {
	
	@Autowired
	private com.example.demo.dao.EtudiantRepository etudiantRepository;
	@Autowired
	private AccountServiceImpl accountService;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private EnseignantRepository enseignantChercheur;
	
	@GetMapping("/etudiants")
	public List<Etudiant> getContacts(){
		return etudiantRepository.findAll();
	}
	
	@GetMapping("/etudiants/{id}")
	public Optional<Etudiant> getContact(@PathVariable Long id) {
		return etudiantRepository.findById(id);
	}
	
	/*
	 * @RequestMapping(value = "/etudiants/{username}", method = RequestMethod.GET)
	public Etudiant loadEtudiantByUsername(@RequestParam(value="username") String username) {
		return etudiantRepository.findByUsername(username);
	}
	 */
	
	@DeleteMapping("/etudiants/{id}")
	public void deleteContact(@PathVariable Long id) {
		etudiantRepository.deleteById(id);
	}
	
	@PostMapping("/etudiant")
	public Etudiant addContact(@RequestBody Etudiant contact){
		return etudiantRepository.save(contact);
	}
	@PostMapping("/registerEtud")
	public AppUser register(@RequestBody UserForm userForm) {
		return accountService.saveEtudiant(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
	}
 @PutMapping("/etudiants/{id}")
	public Etudiant updateContact(@PathVariable Long id, @RequestBody Etudiant contact,@RequestParam("username") String username) {
		contact.setId(id);
		AppUser appUser=appUserRepository.findByUsername(contact.getUsername());
		Collection<AppRole> roles=appUser.getRoles();
		contact.setRoles(roles);
		EnseignantChercheur ens=enseignantChercheur.findByUsername(username);
		
		Etudiant e=new Etudiant();
		e.setCin(contact.getCin());
		e.setCv(contact.getCv());
	    e.setDateInscription(contact.getDateInscription());
	    e.setDateNaissance(contact.getDateNaissance());
	    e.setDiplome(contact.getDiplome());
	    e.setEmail(contact.getEmail());
	    e.setId(id);
	    e.setNom(contact.getNom());
	    e.setPassword(contact.getPassword());
	    e.setPhoto(contact.getPhoto());
	    e.setPrenom(contact.getPrenom());
	    e.setTel(contact.getTel());
	    e.setType("etd");
	    e.setUsername(contact.getUsername());
	    e.setEncadrant(ens);
	    e.setRoles(roles);
		return etudiantRepository.save(e);
	}
 

	/*
	 * @PutMapping("/etudiants/{id}")
	public Etudiant updateContact(@PathVariable Long id, @RequestBody Etudiant contact) {
		contact.setId(id);
		AppUser appUser=appUserRepository.findByUsername(contact.getUsername());
		Collection<AppRole> roles=appUser.getRoles();
		contact.setRoles(roles);		
		return etudiantRepository.save(contact);
	}
	 */
	@GetMapping("/chercherEtudiants")
	public Page<Etudiant> chercher( @RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size){
		return etudiantRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
	}
	
	@GetMapping(path = "/etudiants/photoEtudiant/{id}",produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		Etudiant e =etudiantRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/frontPhotos/products/"+e.getPhoto()));
		
	}
	
	@PostMapping(path = "/etudiants/uploadPhoto/{id}")
	public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
		Etudiant p =etudiantRepository.findById(id).get();
		p.setPhoto(id+".jpg");
		Files.write(Paths.get(System.getProperty("user.home")+"/frontPhotos/products/"+p.getPhoto()), file.getBytes());
		etudiantRepository.save(p);
	}
	@PostMapping(path = "/etudiants/uploadCV/{id}/files")
	public void uploadCV(@RequestParam("file") MultipartFile file,@PathVariable Long id) throws Exception{
		Etudiant p =etudiantRepository.findById(id).get();
        
        System.out.println("Deletion successful.");
        try {
        	  
            Files.deleteIfExists(Paths.get(System.getProperty("user.home")+"/frontPhotos/products/"+p.getCv()));
        }
        catch (IOException e) {
  
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
		//Files.deleteIfExists(Paths.get(System.getProperty("user.home")+"/frontPhotos/products/"+p.getCv()));
		Path root = Paths.get(System.getProperty("user.home")+"/frontPhotos/products/");
		Path resolve = root.resolve(id+"cv.pdf");
		//Files.deleteIfExists(resolve);
		
		p.setCv(id+"cv.pdf");
		Files.copy(file.getInputStream(), resolve);
		etudiantRepository.save(p);
	}
	@GetMapping(path = "/etudiants/cvEtudiant/{id}", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public byte[] getCV(@PathVariable("id") Long id) throws Exception {
		Etudiant e = etudiantRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/frontPhotos/products/" + e.getCv()));
	}
	 @PostMapping(path = "/etudiants/uploadCV/files")
	public void uploadCV(@RequestParam("file") MultipartFile file) throws Exception{
		//Etudiant p =etudiantRepository.findById(id).get();
		Path root = Paths.get(System.getProperty("user.home")+"/frontPhotos/products/");
		Path resolve = root.resolve(2+"cv.pdf");
		//p.setCv(id+"cv.pdf");
		Files.copy(file.getInputStream(), resolve);
		
		//etudiantRepository.save(p);
	}
	/*@PostMapping(path = "/etudiants/uploadCV/files//222")
	    public void uploadFile(@RequestParam("file") MultipartFile file) {
		try {
            Path root = Paths.get(System.getProperty("user.home")+"/frontPhotos/products/");
            Path resolve = root.resolve(file.getOriginalFilename());
            if (resolve.toFile()
                       .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    }

	public void save(MultipartFile file) {
        try {
            Path root = Paths.get(System.getProperty("user.home")+"/frontPhotos/products/");
            Path resolve = root.resolve(file.getOriginalFilename());
            if (resolve.toFile()
                       .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            
        }
    }
	 */
}
@Data
class UserForm{
	
	private String username,password,confirmedPassword;
}
	