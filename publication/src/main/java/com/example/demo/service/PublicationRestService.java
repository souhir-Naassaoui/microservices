package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.PublicationRepository;

import com.example.demo.entities.Publication;


@RestController
@CrossOrigin
public class PublicationRestService {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@GetMapping("/publications")
	public List<Publication> getPublications(){
		return publicationRepository.findAll();
	}
	
	@GetMapping("/publications/{id}")
	public Optional<Publication> getPublication(@PathVariable Long id) {
		return publicationRepository.findById(id);
	}
	
	@DeleteMapping("/publications/{id}")
	public void deletePublication(@PathVariable Long id) {
		publicationRepository.deleteById(id);
	}
	
	@PostMapping("/publication")
	public Publication addPublication(@RequestBody Publication contact){
		return publicationRepository.save(contact);
	}
	
	@PutMapping("/publication/{id}")
	public Publication updatePublication(@PathVariable Long id, @RequestBody Publication contact) {
		contact.setId(id);
		return publicationRepository.save(contact);
	}
	@PostMapping(path = "/publications/upload/{id}/files")
	public void uploadCV(@RequestParam("file") MultipartFile file, @PathVariable Long id,@RequestParam("title") String title) throws Exception {
		Publication p = new Publication();
		p.setAuteurId(id);
		p.setDate(new Date());
		p.setTitle(title);
		p.setSourcePdf(title+".pdf");
		//System.out.println("Deletion successful.");

		Path root = Paths.get(System.getProperty("user.home") + "/frontPhotos/products/");
		Path resolve = root.resolve(title+".pdf");
		// Files.deleteIfExists(resolve);
		Files.copy(file.getInputStream(), resolve);
		publicationRepository.save(p);
	}
	
	 /*
	  *
	 
	@GetMapping(path = "/publications/publicationsById/{idAuteur}", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public byte[] getPubs(@PathVariable("idAuteur") Long idAuteur) throws Exception {
		
		Publication p=(Publication) publicationRepository.findByAuteurId(idAuteur);
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/frontPhotos/products/" +p.getSourcePdf()));
	}
	@GetMapping(path = "/publications/publicationsById/{idAuteur}", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public List<byte[]> getPubs(@PathVariable ("idAuteur") Long idAuteur){
		List<Publication> pubs=publicationRepository.findByAuteurId(idAuteur);
		List<byte[]> bites=new ArrayList<>();
		pubs.forEach(p->{
			try {
				bites.add(Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/frontPhotos/products/" + p.getSourcePdf())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return bites;
	}
	

	  */
	
	@GetMapping(path = "/publications/get/{idAuteur}")
	public List<Publication> findByAuteurId(@PathVariable ("idAuteur") Long idAuteur){
		return publicationRepository.findByAuteurId(idAuteur);	
	}
	
	 @GetMapping(path = "/publications/publicationsById/{idAuteur}", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
		public byte[] getPubs(@PathVariable("idAuteur") Long idAuteur,@RequestParam(name = "name") String name) throws Exception {
			
			//Publication p=(Publication) publicationRepository.findByAuteurId(idAuteur);
			return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/frontPhotos/products/" +name));
		}

}
