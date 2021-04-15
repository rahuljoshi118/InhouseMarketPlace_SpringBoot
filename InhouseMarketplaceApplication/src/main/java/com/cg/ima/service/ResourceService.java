package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entities.Resource;
import com.cg.ima.repository.IResourceRepository;

@Service
public class ResourceService implements IResourceService {

	@Autowired
	private IResourceRepository resourceRepository;
	
	
	
	@Override
	public Resource addResource(Resource resource) {
		
		Resource r = resourceRepository.save(resource);
		return r;

	}

	@Override
	public Resource updateResource(Resource resource) {
		
		Resource r = resourceRepository.save(resource);
		return r;
		
	}

	@Override
	public void removeResource(int resId) {
		
		resourceRepository.deleteById(resId);
		
	}

	@Override
	public Resource getResourceById(int resId) {
		
		Optional<Resource> resource = resourceRepository.findById(resId);

		if (resource.isPresent()) 
		{
			return resource.get();
		} 
		else 
		{
			return null;
		}
		
	}

//if we want all Resources using like query	
	@Override
	public List<Resource> getAllResourcesByCategory(int catId) {
		
		List<Resource> resource = resourceRepository.findByCategory(catId);
		return resource;
		
	}



	@Override
	public List<Resource> getAllResources() {
		
		return resourceRepository.findAll();
		
	}


}
