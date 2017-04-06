package com.tyr.tom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyr.tom.service.PostService;

@Controller
@RequestMapping("/blog")
public class HomeController {

	private PostService postService;

	@Autowired
	public HomeController(PostService postService) {
		this.postService = postService;
	}

	@RequestMapping
	public String greetings(Model model, @PageableDefault(size = 10, page = 0) Pageable pageable) {
		model.addAttribute("greetings", "Witam na blogu");
		model.addAttribute("posts", postService.getPostsByCreationDateDesc(pageable));

		return "index";
	}

	@RequestMapping("/aboutMe")
	public String aboutMe(Model model) {
		
		return "aboutMe";
	}

}
