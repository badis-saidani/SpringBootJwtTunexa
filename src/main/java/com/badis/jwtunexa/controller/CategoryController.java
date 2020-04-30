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

import com.badis.jwtunexa.model.Category;
import com.badis.jwtunexa.repository.CategoryRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	  @GetMapping("/categories") public Collection<Category> getCategorys() {
	  return categoryRepository.findAll(); }
	 
	
	
	@PostMapping("/category")
	public Category save(@RequestBody Category category) {

		// save category instance to db
		return categoryRepository.save(category);
	}
	
	@PutMapping("/category")
	public Category update(@RequestBody Category category) {

		// save category instance to db
		return categoryRepository.save(category);
	}
	
	@DeleteMapping("/categories/{id}")
	public boolean delete(@PathVariable String id) {

		// save category instance to db
		categoryRepository.deleteById(Long.parseLong(id));
		return true;
	}
}

