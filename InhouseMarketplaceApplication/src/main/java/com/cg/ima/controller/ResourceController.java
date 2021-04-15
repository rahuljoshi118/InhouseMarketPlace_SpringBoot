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

import com.cg.ima.entities.Resource;
import com.cg.ima.exception.ResourceNotFoundException;
import com.cg.ima.service.ResourceService;

@RestController
@RequestMapping("/ima")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;

	
// if we want to post(insert) multiple resources	
//	@PostMapping(path = "/resource")
//	public List<Resource> addResource(@RequestBody List<Resource> resources) {
//		
//		for(Resource r: resources)
//		{
//			Resource res = resourceService.addResource(r);
//		}		
//		return resources;
//		
//	}

// if we want to post(insert) a single resource
	@PostMapping(path = "/resource")
	public Resource addResource(@RequestBody Resource res) {
	
		Resource resource = resourceService.addResource(res);		
		return resource;
		
	}
	
	@PutMapping(path = "/resource")
	public Resource updateResource(@RequestBody Resource res) {
		
		Resource resource = resourceService.updateResource(res);
		return resource;
		
	}

	@DeleteMapping(path = "/resource/{resId}")
	public void removeResource(@PathVariable("resId") int resId) {
		
		resourceService.removeResource(resId);

	}

	@GetMapping(path = "/resource/{resId}")
	public Resource getResourceById(@PathVariable("resId") int resId) throws ResourceNotFoundException {
	
		Resource resource = resourceService.getResourceById(resId);
		// Throw user defined exception
		if (resource == null) {
			ResourceNotFoundException resourceNotFound = new ResourceNotFoundException("Resource ID '"+ resId +"' Not Found!");
			throw resourceNotFound;
		}
		else
		{	
			return resource;
		}
	}

//if we want all resources using like query	
		@GetMapping(path = "/resource/category/{catId}")
		public List<Resource> getAllResourcesByCategory(@PathVariable("catId") int catId) throws ResourceNotFoundException {
			
			List<Resource> resources = resourceService.getAllResourcesByCategory(catId);
			if (resources.isEmpty()) {
				ResourceNotFoundException resourceNotFound = new ResourceNotFoundException("Resource For Category Id '"+ catId +"' Not Found!");
				throw resourceNotFound;
			}
			else
			{	
				return resources;
			}
		
		}
	
	@GetMapping(path = "/resource")
	public List<Resource> getAllResources() throws ResourceNotFoundException {
		
		List<Resource> resources = resourceService.getAllResources();
		if (resources.isEmpty()) {
			ResourceNotFoundException resourceNotFound = new ResourceNotFoundException("No Resource Found!");
			throw resourceNotFound;
		}
		else
		{	
			return resources;
		}
	}

}
