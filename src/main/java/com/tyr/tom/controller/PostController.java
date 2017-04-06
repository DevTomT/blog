package com.tyr.tom.controller;

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

import com.tyr.tom.domain.Comment;
import com.tyr.tom.domain.Post;
import com.tyr.tom.service.CategoryService;
import com.tyr.tom.service.PostService;

@Controller
@RequestMapping("/blog")
public class PostController {

	private PostService postService;
	private CategoryService categoryService;

	@Autowired
	public PostController(PostService postService, CategoryService categoryService) {
		this.postService = postService;
		this.categoryService = categoryService;
	}

	@RequestMapping("/archive")
	public String getPostList(Model model) {
		model.addAttribute("postList", postService.getPostList());

		return "archive";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addPost", method = RequestMethod.GET)
	public String getAddNewPost(Model model) {
		Post newPost = new Post();
		model.addAttribute("newPost", newPost);
		model.addAttribute("categories", categoryService.getCategoryListOrderByCategoryName());

		return "addPost";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public String processAddNewPost(@ModelAttribute("newPost") @Valid Post newPost, BindingResult bindingResult,
			Model model) {
		model.addAttribute("categories", categoryService.getCategoryList());

		if (bindingResult.hasErrors()) {
			return "addPost";
		}

		postService.addNewPost(newPost);

		return "redirect:/blog";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/{postId}/deletePost")
	public String deletePost(@PathVariable Long postId, Model model) throws Exception {
		postService.deletePostById(postId);

		return "redirect:/blog";
	}

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public String getPost(Model model, @PathVariable Long postId) throws Exception {

		Post post = postService.getPostById(postId);
		model.addAttribute("post", post);

		Comment newComment = new Comment();
		newComment.setPost(post);
		model.addAttribute("newComment", newComment);

		return "post";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{postId}/editPost", method = RequestMethod.GET)
	public String getEditPost(Model model, @PathVariable Long postId) throws Exception {
		model.addAttribute("postToBeEdited", postService.getPostById(postId));
		model.addAttribute("categories", categoryService.getCategoryList());

		return "editPost";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{postId}/editPost", method = RequestMethod.POST)
	public String processEditPost(@ModelAttribute("postToBeEdited") @Valid Post postToBeEdited,
			BindingResult bindingResult, Model model) {
		model.addAttribute("categories", categoryService.getCategoryListOrderByCategoryName());

		if (bindingResult.hasErrors()) {
			return "editPost";
		}

		postService.editPost(postToBeEdited);

		return "redirect:/blog";

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");

		return mav;
	}

}
