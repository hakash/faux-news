package com.dittdesign.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dittdesign.blog.models.Article;
import com.dittdesign.blog.models.ArticleRepository;

@Controller
public class MainController {
	
	@Autowired
	private ArticleRepository repo;
	
	@GetMapping({"/","/home"})
	String home(Model model) {
		
		List<Article> articles = repo.findByType(Sort.by(Sort.Direction.DESC,"created"),"article");
//		System.out.println(articles);
		if(articles.size() < 1) return "home";
		
		Article heroArticle = articles.remove(0);
		PageRequest pgRequest = PageRequest.of(0, 20, Sort.Direction.DESC,"created");
		List<Article> notices = repo.findByType(pgRequest,"notice");

		
		model.addAttribute( "heroArticle", heroArticle);
		model.addAttribute( "articlesWithImages", articles);
		model.addAttribute( "notices", notices);
		return "home";
	}
	
	@GetMapping("/about")
	String about() {
		return "about";
	}
	
	@GetMapping("/403")
	String forbidden() {
		return "403";
	}
}