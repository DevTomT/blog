package com.tyr.tom.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tyr.tom.domain.Category;
import com.tyr.tom.service.CategoryService;

@Controller
@RequestMapping("/blog/categories")
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping
	public String getCategoryList(Model model) {
		model.addAttribute("categoryList", categoryService.getCategoryListOrderByCategoryName());

		return "categories";
	}

	@RequestMapping("/{categoryId}")
	public String getCategory(Model model, @PathVariable Long categoryId) throws Exception {
		model.addAttribute("category", categoryService.getCategoryById(categoryId));

		return "category";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String getAddNewCategory(Model model) {
		Category category = new Category();
		model.addAttribute("newCategory", category);

		return "addCategory";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String processAddNewCategory(@ModelAttribute("newCategory") @Valid Category newCategory,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors() || categoryService.checkIfCategoryExist(newCategory.getCategoryName(), bindingResult)) {
				return "addCategory";
			}

		} catch (EntityNotFoundException e) {
			categoryService.addNewCategory(newCategory);
		}

		return "redirect:/blog/categories";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{categoryId}/editCategory", method = RequestMethod.GET)
	public String getEditCategory(Model model, @PathVariable Long categoryId) throws Exception {
		model.addAttribute("categoryToBeEdited", categoryService.getCategoryById(categoryId));

		return "editCategory";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{categoryId}/editCategory", method = RequestMethod.POST)
	public String processEditCategory(Model model,
			@ModelAttribute("categoryToBeEdited") @Valid Category categoryToBeEdited, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editCategory";
		}

		categoryService.editCategory(categoryToBeEdited);

		return "redirect:/blog/categories";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/{categoryId}/deleteCategory")
	public String deleteCategory(@PathVariable("categoryId") Long categoryId, Model model) throws Exception {
		categoryService.deleteCategoryById(categoryId);

		return "redirect:/blog/categories";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");

		return mav;
	}

}
