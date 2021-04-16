package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.ima.entities.Category;
import com.cg.ima.exception.CategoryExistsException;
import com.cg.ima.exception.CategoryNotFoundException;
import com.cg.ima.repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	
	
	@Override
	public Category addCategory(Category category) throws CategoryExistsException {
		
		Category searchedCategory = categoryRepository.findBySpecificCategoryName(category.getCatName());

		if (searchedCategory != null && category.getCatName().equals(searchedCategory.getCatName())) 
		{
			throw new CategoryExistsException("Category with name '"+category.getCatName()+"' already exists!");
		}
		else
		{
			Category cat = categoryRepository.save(category);
			return cat;
		}

	}

	@Override
	public Category updateCategory(Category category) {
		
		Category cat = categoryRepository.save(category);
		return cat;
		
	}

	@Override
	public ResponseEntity<String> removeCategory(int catId) throws CategoryNotFoundException {
		
		Optional<Category> searchedCategory = categoryRepository.findById(catId);

		if (!searchedCategory.isPresent()) 
		{
			throw new CategoryNotFoundException("Category ID '"+ catId +"' Doesn't Exists!");
		}
		else
		{
			categoryRepository.deleteById(catId);
			return ResponseEntity.ok("Category Id '"+catId+"' has been deleted!");
		}
		
	}

	@Override
	public Category getCategoryById(int catId) throws CategoryNotFoundException {
		
		Optional<Category> cat = categoryRepository.findById(catId);

		if (cat.isPresent()) 
		{
			return cat.get();
		} 
		else 
		{
			throw new CategoryNotFoundException("Category ID '"+ catId +"' Not Found!");
		}
		
	}

//if we want all categories using like query	
//	@Override
//	public List<Category> getAllCategoriesByName(String catName) {
//		
//		List<Category> cat = categoryRepository.findByName(catName);
//		return cat;
//		
//	}

// if we want a single category by their full name		
	@Override
	public Category getCategoryByName(String catName) throws CategoryNotFoundException {
		
		Optional<Category> cat = categoryRepository.findByName(catName);

		if (cat.isPresent()) 
		{
			return cat.get();
		}
		else
		{
			throw new CategoryNotFoundException("Category Name '"+ catName +"' Not Found!");
		}
		
	}


	@Override
	public List<Category> getAllCategories() throws CategoryNotFoundException {
		
		List<Category> categories = categoryRepository.findAll();
		if (categories.isEmpty()) 
		{
			throw new CategoryNotFoundException("No Category Found!");
		}
		else
		{	
			return categories;
		}
		
	}


}
