package com.tyr.tom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyr.tom.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findAllByOrderByCategoryName();

	Optional<Category> findByCategoryName(String categoryName);
	
}
