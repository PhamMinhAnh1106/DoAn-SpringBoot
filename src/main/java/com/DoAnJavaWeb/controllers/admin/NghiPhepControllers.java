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
import com.DoAnJavaWeb.models.NghiPhep;
import com.DoAnJavaWeb.service.NghiPhepService;
@Controller
@RequestMapping("/admin")
public class NghiPhepControllers {
	@Autowired
	private NghiPhepService nghiPhepService;
	@GetMapping("/listnghiphep")
	public String index(Model model) {
		List<NghiPhep> listNghiPhep = this.nghiPhepService.getAll();
		model.addAttribute("listnp", listNghiPhep);
		return "admin/listnghiphep/indexNP";
	}
	@GetMapping("add-nghiphep")
	public String add(Model model) {
		NghiPhep nghiPhep = new NghiPhep();
		model.addAttribute("dsnp", nghiPhep);
		return "admin/listnghiphep/addnp";
	}
	@GetMapping("/edit-nghiphep/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		NghiPhep nghiPhep = nghiPhepService.findById(id);
		if (nghiPhep != null) {
			model.addAttribute("dsnp", nghiPhep);
			return "admin/listnghiphep/editnp";
		} else {
			return "redirect:/admin/listnghiphep";
		}
	}
	@PostMapping("/edit-nghiphep")
	public String update(@ModelAttribute("dsnp") NghiPhep nghiPhep) {
		if (this.nghiPhepService.update(nghiPhep)) {
			return "redirect:/admin/listnghiphep";
		} else {
			return "admin/listnghiphep/editnp";
		}
	}
	@GetMapping("/delete-nghiphep/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.nghiPhepService.delete(id);
		return "redirect:/admin/listnghiphep";
	}
}