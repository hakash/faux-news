package com.dittdesign.blog.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dittdesign.blog.models.Article;
import com.dittdesign.blog.models.ArticleRepository;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository repo;
	
	@GetMapping({"/article","/article/{sid}"})
	public String getArticle(Model model, HttpServletRequest request, @PathVariable Optional<String> sid) {
		if(sid.isEmpty()) return "article";
				
		try {
			Long id = Long.parseLong(sid.get());
			
			Optional<Article> result = repo.findById(id);
			if(result.isEmpty()) return "article";
			
			Article article = result.get();

			model.addAttribute("article", article);
		}
		catch(NumberFormatException e) {
			System.out.println(String.format("URL ID: %s ## URL: %s", sid.get(), request.getRequestURI()));			
		}
		
		return "article";
	}
	
	@GetMapping("/article-list")
	public String getMyArticles(Model model, Principal principal) {
		List<Article> articles = new ArrayList<>();
		List<Article> notices = new ArrayList<>();
		
		if(principal != null) {
			articles = repo.findByAuthorAndType(principal.getName(),"article");
			notices = repo.findByAuthorAndType(principal.getName(),"notice");
		}
		
		model.addAttribute("articles", articles);
		model.addAttribute("notices", notices);
		
		return "article-list";
	}
	

}
