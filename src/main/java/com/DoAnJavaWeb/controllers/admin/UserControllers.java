package com.DoAnJavaWeb.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControllers {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
}
