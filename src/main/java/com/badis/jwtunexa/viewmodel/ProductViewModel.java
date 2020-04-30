package com.badis.jwtunexa.viewmodel;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.badis.jwtunexa.model.Category;
import com.badis.jwtunexa.model.Devis;
import com.badis.jwtunexa.model.FileModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProductViewModel {

	private Long id;
	private String name;
	
    private FileModel fileModel;
	private String description;
	
	private Long categoryId;
	private Collection<Devis> devises;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FileModel getFileModel() {
		return fileModel;
	}
	public void setFileModel(FileModel fileModel) {
		this.fileModel = fileModel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Collection<Devis> getDevises() {
		return devises;
	}
	public void setDevises(Collection<Devis> devises) {
		this.devises = devises;
	}
	
	
}
