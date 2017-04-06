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
import com.tyr.tom.service.CommentService;
import com.tyr.tom.service.PostService;

@Controller
@RequestMapping("/blog")
public class CommentController {

	private CommentService commentService;
	private PostService postService;

	@Autowired
	public CommentController(CommentService commentService, PostService postService) {
		this.commentService = commentService;
		this.postService = postService;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{postId}", method = RequestMethod.POST)
	public String processAddNewComment(@ModelAttribute("newComment") @Valid Comment newComment,
			BindingResult bindingResult, Model model, @PathVariable Long postId) throws Exception {

		if (bindingResult.hasErrors()) {
			Post post = postService.getPostById(postId);
			model.addAttribute("post", post);

			return "post";
		}

		commentService.addNewComment(newComment);

		return "redirect:/blog/{postId}";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{postId}/deleteComment", method = RequestMethod.POST)
	public String deleteComments(Comment commentToDelete) throws Exception {
		commentService.deleteComment(commentToDelete);

		return "redirect:/blog/{postId}";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");

		return mav;
	}

}
