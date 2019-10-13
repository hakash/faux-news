package com.dittdesign.blog.controllers;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dittdesign.blog.models.Article;
import com.dittdesign.blog.models.ArticleRepository;

@Controller
public class EditorController {
	
	@Autowired
	private ArticleRepository repo;
	
	@GetMapping({"/editor","/editor/{sid}"})
	public String article(Principal principal, Model model, @PathVariable Optional<String> sid) {
		if(sid.isEmpty()) return "editor";
		
		Long id = Long.parseLong(sid.get());
		
		Optional<Article> result = repo.findById(id);
		if(result.isEmpty()) return "editor";
		
		Article article = result.get();
		
		model.addAttribute("article", article);
		
		return "editor";
	}
	
	
	@PostMapping("/editor")
	public ModelAndView newArticle(Principal principal, HttpServletRequest request, Article article) {
		System.out.println(article.getAuthor() + " -- " + principal.getName());
		
		if(article.getAuthor() == null && article.getId() == 0) {
			article.setAuthor(principal.getName());
		}
		
		if(!article.getImageUrl().startsWith("http")) {
			article.setImageUrl("http://" + article.getImageUrl());
		}
		
		if(article.getAuthor().equalsIgnoreCase(principal.getName()) || request.isUserInRole("ADMIN")) {
			Article result = repo.save(article);
			return new ModelAndView("redirect:/article/" + result.getId());
		}
		System.out.println(String.format( "Wrong author attempting to edit article: %s, %d", principal.getName(), article.getId() ));
		return new ModelAndView("redirect:/editor");
	}

	

}
