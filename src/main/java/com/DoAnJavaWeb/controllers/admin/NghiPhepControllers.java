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

import com.DoAnJavaWeb.models.LeaveStatus;
import com.DoAnJavaWeb.models.NghiPhep;
import com.DoAnJavaWeb.service.EmailService;
import com.DoAnJavaWeb.service.NghiPhepService;
import com.DoAnJavaWeb.service.UserService;

@Controller
@RequestMapping("/admin")
public class NghiPhepControllers {
	@Autowired
	private NghiPhepService nghiPhepService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService usersService;
	
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

//	@PostMapping("/edit-nghiphep")
//	public String update(@ModelAttribute("dsnp") NghiPhep nghiPhep) {
//		if (this.nghiPhepService.update(nghiPhep)) {
//			if (nghiPhep.getStatus() == LeaveStatus.approved) { //nếu status của thằng này là approved
//				String email = nghiPhep.getEmployee().getEmail(); 
////				if (email != null && !email.isEmpty()) { 
//					emailService.sendMail( "\"Đồ Án Java Web gửi mail\" <phamminhanh11623@gmail.com>", 
//							email, "[- Xác Nhận Phép -]", 
//							"Đơn xin nghỉ phép của bạn đã được duyệt !" ); 
////					}
//				} else if (nghiPhep.getStatus() == LeaveStatus.rejected) { //nếu status của thằng này là rêjected
//					String email = nghiPhep.getEmployee().getEmail(); 
////					if (email != null && !email.isEmpty()) { 
//						emailService.sendMail( "\"Đồ Án Java Web gửi mail\" <phamminhanh11623@gmail.com>",
//								email, "[- Xác Nhận Phép -]", 
//								"Đơn xin nghỉ phép của bạn đã bị từ chối." ); 
////						}
//			}
//			return "redirect:/admin/listnghiphep";
//		} else {
//			return "admin/listnghiphep/editnp";
//		}
//	}
	

	@PostMapping("/edit-nghiphep")
	public String update(@ModelAttribute("dsnp") NghiPhep nghiPhep) {
	    if (this.nghiPhepService.update(nghiPhep)) {
	        // Lấy email từ service
	        String email = usersService.getEmailById(nghiPhep.getEmployee().getId());
	        String from = "\"Đồ Án Java Web gửi mail\" <phamminhanh11623@gmail.com>";
	        String subject = null;
	        String text = null;

	        if (nghiPhep.getStatus() == LeaveStatus.approved) {
	            subject = "[- Xác Nhận Phép -]";
	            text = "Đơn xin nghỉ phép của bạn đã được duyệt !";
	        } else if (nghiPhep.getStatus() == LeaveStatus.rejected) {
	            subject = "[- Xác Nhận Phép -]";
	            text = "Đơn xin nghỉ phép của bạn đã bị từ chối.";
	        }

	        // In log các giá trị tham số
	        System.out.println("from: " + from);
	        System.out.println("email: " + email);
	        System.out.println("subject: " + subject);
	        System.out.println("text: " + text);

	        if (email != null && !email.isEmpty() && from != null && !from.isEmpty() && subject != null && text != null) {
	            emailService.sendMail(from, email, subject, text);
	        } else {
	            System.out.println("send mail error: null value detected in email parameters.");
	        }

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
