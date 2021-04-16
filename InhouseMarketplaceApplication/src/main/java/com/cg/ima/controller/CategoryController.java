package com.cg.ima.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entities.Category;
import com.cg.ima.exception.CategoryExistsException;
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
	public Category addCategory(@Valid @RequestBody Category category, BindingResult bindingResult) throws Exception, CategoryExistsException {
	
		if (bindingResult.hasErrors()) 
		{
			throw new Exception("Category details are not valid");
		}
		
		Category c = categoryService.addCategory(category);		
		return c;
		
	}
	
	@PutMapping(path = "/category")
	public Category updateCategory(@Valid @RequestBody Category category, BindingResult bindingResult) throws Exception {
		
		if (bindingResult.hasErrors()) 
		{
			throw new Exception("Category details are not valid");
		}
		
		Category c = categoryService.updateCategory(category);
		return c;
		
	}

	@DeleteMapping(path = "/category/{catId}")
	public ResponseEntity<String> removeCategory(@PathVariable("catId") int catId) throws CategoryNotFoundException {
		
		return categoryService.removeCategory(catId);

	}

	@GetMapping(path = "/category/{catId}")
	public Category getCategoryById(@PathVariable("catId") int catId) throws CategoryNotFoundException {
	
		Category category = categoryService.getCategoryById(catId);
		return category;
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
		return category;	
	}


	@GetMapping(path = "/category")
	public List<Category> getAllCategories() throws CategoryNotFoundException {
		
		List<Category> categories = categoryService.getAllCategories();
		return categories;
	}

}
