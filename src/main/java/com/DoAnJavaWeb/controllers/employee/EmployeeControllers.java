package com.DoAnJavaWeb.controllers.employee;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee")
public class EmployeeControllers {
	@GetMapping
	public String index() {
		return "redirect:/employee/";
	}

	@RequestMapping("/")
	public String employee() {
		return "employee/index";
	}

}
