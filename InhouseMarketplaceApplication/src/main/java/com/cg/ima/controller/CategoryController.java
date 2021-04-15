package com.cg.ima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entities.Category;
import com.cg.ima.exception.CategoryNotFoundException;
import com.cg.ima.service.CategoryService;

@RestController
@RequestMapping("/ima")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

// if we want to post(insert) multiple categories	
//		@PostMapping(path = "/category")
//		public List<Category> addCategory(@RequestBody List<Category> categories) {
//			
//			for(Category c: categories)
//			{
//				Category cat = categoryService.addCategory(c);
//			}		
//			return categories;
//			
//		}

// if we want to post(insert) a single category
	@PostMapping(path = "/category")
	public Category addCategory(@RequestBody Category category) {
	
		Category c = categoryService.addCategory(category);		
		return c;
		
	}
	
	@PutMapping(path = "/category")
	public Category updateCategory(@RequestBody Category category) {
		
		Category c = categoryService.updateCategory(category);
		return c;
		
	}

	@DeleteMapping(path = "/category/{catId}")
	public void removeCategory(@PathVariable("catId") int catId) {
		
		categoryService.removeCategory(catId);

	}

	@GetMapping(path = "/category/{catId}")
	public Category getCategoryById(@PathVariable("catId") int catId) throws CategoryNotFoundException {
	
		Category category = categoryService.getCategoryById(catId);
		// Throw user defined exception
		if (category == null) {
			CategoryNotFoundException categoryNotFound = new CategoryNotFoundException("Category ID '"+ catId +"' Not Found!");
			throw categoryNotFound;
		}
		else
		{	
			return category;
		}
		
	}

//if we want all categories using like query	
//	@GetMapping(path = "/category/name/{catName}")
//	public List<Category> getAllCategoriesByName(@PathVariable("catName") String catName) {
//		
//		List<Category> categories = categoryService.getAllCategoriesByName(catName);
//		return categories;
//	
//	}

// if we want a single category by their full name	
	@GetMapping(path = "/category/name/{catName}")
	public Category getCategoryByName(@PathVariable("catName") String catName) throws CategoryNotFoundException {
		
		Category category = categoryService.getCategoryByName(catName);
		// Throw user defined exception
		if (category == null) {
			CategoryNotFoundException categoryNotFound = new CategoryNotFoundException("Category Name '"+ catName +"' Not Found!");
			throw categoryNotFound;
		}
		else
		{	
			return category;
		}
	
	}


	@GetMapping(path = "/category")
	public List<Category> getAllCategories() throws CategoryNotFoundException {
		
		List<Category> categories = categoryService.getAllCategories();
		if (categories.isEmpty()) {
			CategoryNotFoundException categoryNotFound = new CategoryNotFoundException("No Category Found!");
			throw categoryNotFound;
		}
		else
		{	
			return categories;
		}
	}

}
