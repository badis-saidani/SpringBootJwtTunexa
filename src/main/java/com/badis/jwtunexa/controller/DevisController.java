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

import com.badis.jwtunexa.model.Devis;
import com.badis.jwtunexa.repository.DevisRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DevisController {

	@Autowired
	private DevisRepository devisRepository;
	
	@GetMapping("/devises")
	public Collection<Devis> getDevises() {
		return devisRepository.findAll();
	}
	
	
	/*
	 * @PostMapping("/devis") public Devis save(@RequestBody Devis devis) {
	 * 
	 * // save devis instance to db return devisRepository.save(devis); }
	 */
	
	@PostMapping("/devis")
	public Devis update(@RequestBody Devis devis) {

		// save devis instance to db
		return devisRepository.save(devis);
	}
	
	@DeleteMapping("/devises/{id}")
	public boolean delete(@PathVariable String id) {

		// save devis instance to db
		devisRepository.deleteById(Long.parseLong(id));
		return true;
	}
}


