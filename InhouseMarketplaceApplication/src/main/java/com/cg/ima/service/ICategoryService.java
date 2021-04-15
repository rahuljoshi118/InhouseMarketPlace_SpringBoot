package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entities.Category;

public interface ICategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public void removeCategory(int catId);
	
	public List<Category> getAllCategories();

	public Category getCategoryByName(String catName);

	public Category getCategoryById(int catId);

}
