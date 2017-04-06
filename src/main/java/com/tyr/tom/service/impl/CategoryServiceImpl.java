package com.tyr.tom.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tyr.tom.domain.Category;
import com.tyr.tom.repository.CategoryRepository;
import com.tyr.tom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void addNewCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(long categoryId) throws Exception {
		Category found = categoryRepository.findOne(categoryId);
		if (found == null) {
			throw new Exception();
		}

		return found;
	}

	@Override
	public List<Category> getCategoryList() {
		return categoryRepository.findAll();

	}

	@Override
	public void deleteCategoryById(long categoryId) {
		categoryRepository.delete(categoryId);
	}

	@Override
	public Category editCategory(Category categoryToBeEdited) {
		return categoryRepository.save(categoryToBeEdited);
	}

	@Override
	public List<Category> getCategoryListOrderByCategoryName() {
		return categoryRepository.findAllByOrderByCategoryName();
	}

	@Override
	public boolean checkIfCategoryExist(String categoryName, BindingResult bindingResult) {
		categoryRepository.findByCategoryName(categoryName).orElseThrow(EntityNotFoundException::new);
		bindingResult.rejectValue("categoryName", "Category already exist",
				"Kategoria o podanej nazwie już istnieje. Proszę podać inną nazwę.");
		
		return true;
	}

}
