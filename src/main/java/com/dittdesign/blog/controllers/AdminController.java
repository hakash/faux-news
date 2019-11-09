package com.dittdesign.blog.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String getAdmin(Model model, Principal principal, HttpServletRequest request) {
		
		if(!request.isUserInRole("ROLE_ADMIN")) return "403";
		
		model.addAttribute("abc", 1);
		
		return "admin/admin";
	}
}
