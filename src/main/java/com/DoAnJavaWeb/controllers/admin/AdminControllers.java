package com.DoAnJavaWeb.controllers.admin;

import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/admin")
public class AdminControllers {

	@GetMapping
	public String index() {
		return "redirect:/admin/";
	}

	@RequestMapping("/")
	public String admin() {
		return "admin/index";
	}
}
