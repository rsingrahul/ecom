package com.tariq.backend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "image_url", length = 50)
	private String imageURL;
	
	@Column(name = "is_active")
	private boolean active = true;
	
	public Category(){}
	
	public Category(int id, String name, String description, String imageURL, boolean active) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageURL = imageURL;
		this.active = active;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}
