package com.cg.ima.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq")
	@SequenceGenerator(name="category_seq",sequenceName="category_seq", allocationSize=1)
	private int catId;

	@Column(name = "category_name")
	private String catName;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	private List<Resource> resources = new ArrayList<Resource>();

	public Category() {
		super();
	}

	public Category(int catId, String catName, List<Resource> resources) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.resources = resources;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + ", resources=" + resources + "]";
	}
	


}
