package com.DoAnJavaWeb.controllers.admin;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DoAnJavaWeb.models.Role;
import com.DoAnJavaWeb.models.UserRole;
import com.DoAnJavaWeb.models.Users;

import com.DoAnJavaWeb.service.UserService;

@Controller
@RequestMapping("/admin")
public class ListNhanVienControllers {
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/listnhanvien")
	public String index(Model model) {
		List<Users> list = this.userService.getAll();
		model.addAttribute("list", list);
		return "admin/listnhanvien/index";
	}

	@GetMapping("/add-nv")
	public String add(Model model) {
		Users users = new Users();
		users.setGender(true);
		users.setEnabled(true);
		model.addAttribute("dsnv", users);
		return "admin/listnhanvien/addnv";
	}

	@PostMapping("/add-nv")
	public String save(@ModelAttribute("dsnv") Users users, @RequestParam("role") String roleName) {
		if (this.userService.create(users)) {
			Role role = userService.findRoleByName(roleName);
			if (role == null) {
				return "admin/listnhanvien/addnv"; // Bắt lỗi null role lần thử thứ n* -> thành công rồi
			}

			//Băm mật khẩu
			String rawPass = users.getPassword();
			String encodePassword = bCryptPasswordEncoder.encode(rawPass);
			users.setPassword(encodePassword);

			UserRole userRole = new UserRole(users, role);
			this.userService.saveUserRole(userRole);

			return "redirect:/admin/listnhanvien";
		} else {
			return "admin/listnhanvien/addnv";
		}
	}

	@GetMapping("/edit-nv/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {

		Users users = this.userService.findById(id);
		model.addAttribute("dsnv", users);
		return "admin/listnhanvien/editnv";
	}

	@PostMapping("/edit-nv")
	public String update(@ModelAttribute("dsnv") Users users) {
		Users existingUser = this.userService.findById(users.getId());
		if (existingUser != null) {
			// Nếu mật khẩu không thay đổi, giữ nguyên mật khẩu cũ
			if (users.getPassword() == null || users.getPassword().isEmpty()
					|| existingUser.getPassword().equals(users.getPassword())) {
				users.setPassword(existingUser.getPassword());
			} else {
				// Nếu mật khẩu mới được nhập, băm mật khẩu mới
				String rawPass = users.getPassword();
				String encodePassword = bCryptPasswordEncoder.encode(rawPass);
				users.setPassword(encodePassword);
			}
			this.userService.update(users); 
			return "redirect:/admin/listnhanvien";
		}

		return "admin/listnhanvien/editnv";
	}

	@GetMapping("/delete-nv/{id}")
	public String delete(@PathVariable("id") Integer id) {
		if (this.userService.delete(id))
			return "redirect:/admin/listnhanvien";
		else
			return "redirect:/admin/listnhanvien";
	}

}
