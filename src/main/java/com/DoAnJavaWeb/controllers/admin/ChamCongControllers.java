package com.DoAnJavaWeb.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DoAnJavaWeb.models.ChamCong;
import com.DoAnJavaWeb.service.ChamCongService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
@RequestMapping("/admin")
public class ChamCongControllers {
	@Autowired
	private ChamCongService chamCongService;

	@GetMapping("listchamcong")
	public String index(Model model) {
		List<ChamCong> listcc = this.chamCongService.getAll();
		model.addAttribute("listcc", listcc);
		return "admin/listchamcong/indexLCC";
	}

	@GetMapping("add-chamcong")
	public String add(Model model) {
		ChamCong chamcong = new ChamCong();
		chamcong.setEnabled(true);
		model.addAttribute("dscc", chamcong);
		return "admin/listchamcong/addcc";
	}

	@PostMapping("/add-chamcong")
	public String save(@ModelAttribute("chamCong") ChamCong chamCong) {
		if (this.chamCongService.create(chamCong)) {
			return "redirect:admin/listchamcong";
		} else {
			return "admin/listchamcong/addcc";
		}
	}

	@GetMapping("/edit-chamcong/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		ChamCong chamCong = chamCongService.findById(id);
		if (chamCong != null) {
			model.addAttribute("chamCong", chamCong);// Chấm công khác null thì lụm
			return "admin/listchamcong/editcc";
		} else {
			return "redirect:admin/listchamcong";
		}
	}

	@PostMapping("/edit-chamcong")
	public String update(@ModelAttribute("chamCong") ChamCong chamCong) {
		if (this.chamCongService.update(chamCong)) {
			return "redirect:admin/listchamcong";
		} else {
			return "admin/listchamcong/editcc";
		}
	}

	@GetMapping("/delete-chamcong/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.chamCongService.delete(id);
		return "redirect:admin/listchamcong";
	}
}
