package com.cg.ima;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.ima.entities.Category;
import com.cg.ima.exception.CategoryExistsException;
import com.cg.ima.exception.CategoryNotFoundException;
import com.cg.ima.repository.ICategoryRepository;
import com.cg.ima.service.CategoryService;



@ExtendWith(MockitoExtension.class)//Junit 5
public class CategoryServiceTest {

	@Mock
	ICategoryRepository categoryRepository;
	
	
	@InjectMocks
	CategoryService categoryService;
	
	
	@Test
	public void testAddCategory() throws CategoryExistsException
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		
		Mockito.when(categoryRepository.save(cat)).thenReturn(cat);
		assertEquals(cat, categoryService.addCategory(cat));
	}
	
	@Test
	public void testUpdateCategory()
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");		
		
		cat.setCatName("Electronics");
		Mockito.when(categoryRepository.save(cat)).thenReturn(cat);
		
		assertEquals(cat,categoryService.updateCategory(cat));
		
	}
	
	@Test
	public void testDeleteCategory()
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		
		Mockito.when(categoryRepository.existsById(cat.getCatId())).thenReturn(false);
	    assertFalse(categoryRepository.existsById(cat.getCatId()));
	}
	
	
	@Test
	public void testGetCategoryById() throws CategoryNotFoundException
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		
		Mockito.when(categoryRepository.findById(10)).thenReturn(Optional.of(cat));
		Category actualcat = categoryService.getCategoryById(10);
		assertEquals(10,actualcat.getCatId());
	}

	
	@Test
	public void testGetCategoryByName() throws CategoryNotFoundException
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Mockito.when(categoryRepository.findByName("Clothing")).thenReturn(Optional.of(cat));
		Category actualCat = categoryService.getCategoryByName("Clothing");
		assertEquals("Clothing",actualCat.getCatName());
	}
	

	@Test
	public void testGetAllCategories()
	{
		List<Category> categories = new ArrayList<>();
		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		Exception ex = assertThrows(CategoryNotFoundException.class, ()-> categoryService.getAllCategories());//assertThrows(Class<T> expectedType,Executable executable: inside this execute() method is present)
		assertEquals("No Category Found!", ex.getMessage());
		
	}
}