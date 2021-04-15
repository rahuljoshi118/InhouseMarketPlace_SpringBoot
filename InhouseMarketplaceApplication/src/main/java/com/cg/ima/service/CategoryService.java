package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entities.Category;
import com.cg.ima.repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	
	
	@Override
	public Category addCategory(Category category) {
		
		Category cat = categoryRepository.save(category);
		return cat;

	}

	@Override
	public Category updateCategory(Category category) {
		
		Category cat = categoryRepository.save(category);
		return cat;
		
	}

	@Override
	public void removeCategory(int catId) {
		
		categoryRepository.deleteById(catId);
		
	}

	@Override
	public Category getCategoryById(int catId) {
		
		Optional<Category> cat = categoryRepository.findById(catId);

		if (cat.isPresent()) 
		{
			return cat.get();
		} 
		else 
		{
			return null;
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
	public Category getCategoryByName(String catName) {
		
		Optional<Category> cat = categoryRepository.findByName(catName);

		if (cat.isPresent()) {
			return cat.get();
		}

		return null;
		
	}


	@Override
	public List<Category> getAllCategories() {
		
		return categoryRepository.findAll();
		
	}


}
