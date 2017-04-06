package com.tyr.tom.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.tyr.tom.domain.Category;

public interface CategoryService {

	void addNewCategory(Category category);

	Category getCategoryById(long categoryId) throws Exception;

	List<Category> getCategoryList();

	List<Category> getCategoryListOrderByCategoryName();

	void deleteCategoryById(long categoryId);

	Category editCategory(Category categoryToBeEdited);

	boolean checkIfCategoryExist(String categoryName, BindingResult bindingResult);
	
}
