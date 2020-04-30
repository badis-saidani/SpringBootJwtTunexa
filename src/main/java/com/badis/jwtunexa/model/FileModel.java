package com.badis.jwtunexa.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="file_model")
public class FileModel implements Serializable{
	@Id
	@GeneratedValue
    @Column(name = "id")
	@JsonView(View.FileInfo.class)
    private Long id;
	
    @Column(name = "name")
    @JsonView(View.FileInfo.class)
	private String name;
    
    @Column(name = "mimetype")
	private String mimetype;
    
    @Column(name = "chemin")
    private String chemin;
    
	
	@Lob
    @Column(name="pic")
    private byte[] pic;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	public FileModel(){}
	
	public FileModel(String name, String mimetype, byte[] pic){
		this.name = name;
		this.mimetype = mimetype;
		this.pic = pic;
	}
	
	
	
	public FileModel(Long id, String name, String mimetype, String chemin, byte[] pic, Product product) {
		super();
		this.id = id;
		this.name = name;
		this.mimetype = mimetype;
		this.chemin = chemin;
		this.pic = pic;
		this.product = product;
	}

	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getMimetype(){
		return this.mimetype;
	}
	
	public void setMimetype(String mimetype){
		this.mimetype = mimetype;
	}
	
	public byte[] getPic(){
		return this.pic;
	}
	
	public void setPic(byte[] pic){
		this.pic = pic;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "FileModel [id=" + id + ", name=" + name + ", mimetype=" + mimetype + ", chemin=" + chemin + ", pic="
				+ Arrays.toString(pic) + ", product=" + product + "]";
	}
	
	
	
}