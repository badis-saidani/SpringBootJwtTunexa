package com.badis.jwtunexa.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badis.jwtunexa.model.Category;
import com.badis.jwtunexa.model.Devis;
import com.badis.jwtunexa.model.FileModel;
import com.badis.jwtunexa.model.Message;
import com.badis.jwtunexa.model.Product;
import com.badis.jwtunexa.model.View;
import com.badis.jwtunexa.repository.CategoryRepository;
import com.badis.jwtunexa.repository.DevisRepository;
import com.badis.jwtunexa.repository.FileRepository;
import com.badis.jwtunexa.repository.MessageRepository;
import com.badis.jwtunexa.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonView;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/allowed")
public class AllowedControllers {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private DevisRepository devisRepository;
	
	@Autowired
	private ProductRepository prodRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private MessageRepository contactRepository;
	
	@GetMapping("/categories")
	public Collection<Category> getCategorys() {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/products")
	public Collection<Product> getProducts() {
		return prodRepository.findAll();
	}
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable String id) {
		return prodRepository.findById(Long.parseLong(id))
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	}
	
	@GetMapping("/products/{id}")
	public Collection<Product>  getProductsByCat(@PathVariable String id) {
		return prodRepository.findByCategoryId(Long.parseLong(id));
	}
	
	@PostMapping("/devis/{id}")
	public Devis save(@RequestBody Devis devis, @PathVariable Long id) {
		Product prod = prodRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Product not find."));
		
		devis.setProduct(prod);
		// save devis instance to db
		return devisRepository.save(devis);
	}
	
	@PostMapping("/contactus")
	public Message saveContact(@RequestBody Message contact) {
	
		// save devis instance to db
		return contactRepository.save(contact);
	}
	
	 /*
     * Download Files
     */
       @JsonView(View.FileInfo.class)
	@GetMapping("/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		Optional<FileModel> fileOptional = fileRepository.findByProductId(id);
		//prodRepository.findById(id)
		
		if(fileOptional.isPresent()) {
			FileModel file = fileOptional.get();
			//System.out.println("look dis: " + file.toString());
			ResponseEntity<byte[]> result;
			result = ResponseEntity.ok()
					//.header(HttpHeaders.AUTHORIZATION, "'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYWRpcyIsImlhdCI6MTU1NTMzNzM1NSwiZXhwIjoxNTU1NDIzNzU1fQ.R2Y58PfNdzb65GocvrAAZp-1igb41Ie_rXsCgHOchSU8yzz-0zPRqZiJ_n0yt8JMQER-1krpGLi7TtUFZHpnVw")
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
					//.header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnQiLCJpYXQiOjE1NTUzMzg4MTksImV4cCI6MTU1NTQyNTIxOX0.MQ5SJ4jJb27PaeNX7mpARPxr3Naqfy4UO8SfGfzmsrgBu20HDX-r8scc-frnZqqk7YyHSkuEsIBkECPemRTbIA")
					.body(file.getPic());	
			
			System.out.println(result.toString());
			
			return result;
			
		}
		System.out.println("fachal");
		return ResponseEntity.status(404).body(null);
	}
}
