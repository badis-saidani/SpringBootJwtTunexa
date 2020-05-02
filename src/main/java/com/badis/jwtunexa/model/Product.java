package com.badis.jwtunexa.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="products")
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	/*
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	 * "product")
	 * 
	 * @JoinColumn(name = "product_id") private FileModel fileModel;
	 */
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "product")
	@JsonIgnoreProperties("product")
	private FileModel fileModel;

	private String description;
	@ManyToOne
	private Category category;
	@OneToMany(mappedBy="product", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Devis> devises;
	
	
	
	
	public Product() {
	}
	
	
	public Product(Long id, String name, String description, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
	}


	public Product(String name, String description, Category category) {
		this.name = name;
		this.description = description;
		this.category = category;
	}
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@JsonIgnore
	public Collection<Devis> getDevises() {
		return devises;
	}
	public void setDevises(Collection<Devis> devises) {
		this.devises = devises;
	}
	
	

	
	  public FileModel getFileModel() { return fileModel; }
	  
	  
	  public void setFileModel(FileModel fileModel) { this.fileModel = fileModel; }
	 

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category + "]";
	}
	
	
	
}
