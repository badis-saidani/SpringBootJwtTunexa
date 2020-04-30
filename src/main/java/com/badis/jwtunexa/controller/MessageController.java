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

import com.badis.jwtunexa.model.Message;
import com.badis.jwtunexa.repository.MessageRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private MessageRepository contactusRepository;
	
	@GetMapping("/contactuses")
	public Collection<Message> getMessagees() {
		return contactusRepository.findAll();
	}
	
	
	/*
	 * @PostMapping("/contactus") public Message save(@RequestBody Message contactus) {
	 * 
	 * // save contactus instance to db return contactusRepository.save(contactus); }
	 */
	
	@PostMapping("/contactus")
	public Message update(@RequestBody Message contactus) {

		// save contactus instance to db
		return contactusRepository.save(contactus);
	}
	
	@DeleteMapping("/contactuses/{id}")
	public boolean delete(@PathVariable String id) {

		// save contactus instance to db
		contactusRepository.deleteById(Long.parseLong(id));
		return true;
	}
}


