package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entities.Resource;

public interface IResourceService {

	public Resource addResource(Resource resource);
	
	public Resource updateResource(Resource resource);
	
	public void removeResource(int resId);
	
	public Resource getResourceById(int resId);
	
	public List<Resource> getAllResourcesByCategory(int catId);
	
	public List<Resource> getAllResources();
	
}
