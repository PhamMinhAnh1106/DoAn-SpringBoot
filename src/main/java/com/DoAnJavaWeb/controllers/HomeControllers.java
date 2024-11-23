package com.DoAnJavaWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControllers {
	@RequestMapping("")

	public String home() {
		return "login";
	}
}
