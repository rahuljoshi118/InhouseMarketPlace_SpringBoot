package com.cg.ima.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("from Category where category_name like %:catName%") // JPQL -> table name=Entity class
//	List<Category> findByName(@Param("catName") String catName);
	Optional<Category> findByName(@Param("catName") String catName);

}
