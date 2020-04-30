package com.badis.jwtunexa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.badis.jwtunexa.model.Product;
import com.badis.jwtunexa.repository.CategoryRepository;
import com.badis.jwtunexa.repository.ProductRepository;
import com.badis.jwtunexa.viewmodel.ProductViewModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/products")
	public Collection<Product> getProducts() {
		return productRepository.findAll();
	}
	
	
	@PostMapping("/product")
	public Product save(@RequestBody ProductViewModel productVM) {
		
		Product product = new Product(productVM.getName(), productVM.getDescription(),
				categoryRepository.findById(productVM.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find.")));
		// save product instance to db
		return productRepository.save(product);
	}
	
	@PutMapping("/product")
	public Product update(@RequestBody ProductViewModel productVM) {
		Product product = new Product(productVM.getId(), productVM.getName(), productVM.getDescription(),
				categoryRepository.findById(productVM.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find.")));
		System.out.println("chooooo: " + product.toString());
		// save product instance to db
		return productRepository.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	public boolean delete(@PathVariable String id) {

		// save product instance to db
		productRepository.deleteById(Long.parseLong(id));
		return true;
	}
}


