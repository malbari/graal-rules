package com.bezkoder.spring.jpa.h2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tutorial")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	@Version
	public long version;

	@Column(name = "title")
	public String title;

	@Column(name = "description")
	public String description;

	@Column(name = "published")
	public boolean published;

	public Tutorial() {
	}

	public Tutorial(String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}
}
