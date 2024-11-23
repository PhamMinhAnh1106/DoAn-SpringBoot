package com.DoAnJavaWeb.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListNhanVienControllers {
	@RequestMapping("admin/listnhanvien")
	public String index() {
		return "admin/listnhanvien/index";
	}

	@RequestMapping("admin/add-nv")
	public String add() {
		return "admin/listnhanvien/addnv";
	}

}
