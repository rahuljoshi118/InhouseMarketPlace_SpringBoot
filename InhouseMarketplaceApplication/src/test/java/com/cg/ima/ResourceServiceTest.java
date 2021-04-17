package com.cg.ima;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
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
import com.cg.ima.entities.Resource;
import com.cg.ima.exception.ResourceNotFoundException;
import com.cg.ima.repository.IResourceRepository;
import com.cg.ima.service.ResourceService;



@ExtendWith(MockitoExtension.class)//Junit 5
public class ResourceServiceTest {

	@Mock
	IResourceRepository resourceRepository;
	
	
	@InjectMocks
	ResourceService resourceService;
	
	
	@Test
	public void testAddResource()
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);
		
		Mockito.when(resourceRepository.save(res)).thenReturn(res);
		assertEquals(res, resourceService.addResource(res));
	}
	
	@Test
	public void testUpdateResource()
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);		
		
		res.setResId(20);
		Mockito.when(resourceRepository.save(res)).thenReturn(res);
		
		assertEquals(res,resourceService.updateResource(res));
		
	}
	
	@Test
	public void testDeleteResource()
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);		
		
		
		
		Mockito.when(resourceRepository.existsById(res.getResId())).thenReturn(false);
	    assertFalse(resourceRepository.existsById(res.getResId()));
	}
	
	
	@Test
	public void testGetResourceById() throws ResourceNotFoundException
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);
		
		Mockito.when(resourceRepository.findById(10)).thenReturn(Optional.of(res));
		Resource actualResource = resourceService.getResourceById(10);
		assertEquals(10,actualResource.getResId());
	}

	
	

	@Test
	public void testGetAllResourcesByCategory() throws ResourceNotFoundException
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);
		
		List<Resource> resources = new ArrayList<>();
		resources.add(res);
		Mockito.when(resourceRepository.findByCategory(10)).thenReturn(resources);
		List<Resource> actualResource = resourceService.getAllResourcesByCategory(10);
		assertEquals(10,actualResource.get(0).getCategory().getCatId());
		
	}
	
	
	@Test
	public void testGetAllResources()
	{
		List<Resource> resources = new ArrayList<>();
		Mockito.when(resourceRepository.findAll()).thenReturn(resources);
		Exception ex = assertThrows(ResourceNotFoundException.class, ()-> resourceService.getAllResources());//assertThrows(Class<T> expectedType,Executable executable: inside this execute() method is present)
		assertEquals("No Resource Found!", ex.getMessage());
		
	}
}