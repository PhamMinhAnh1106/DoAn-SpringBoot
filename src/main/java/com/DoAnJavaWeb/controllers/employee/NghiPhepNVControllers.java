package com.DoAnJavaWeb.controllers.employee;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.DoAnJavaWeb.models.LeaveStatus;

import com.DoAnJavaWeb.models.ChamCong;
import com.DoAnJavaWeb.models.CustomUserDetails;
import com.DoAnJavaWeb.models.NghiPhep;
import com.DoAnJavaWeb.models.Users;
import com.DoAnJavaWeb.service.NghiPhepService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class NghiPhepNVControllers {

    @Autowired
    private NghiPhepService nghiPhepService;

    @GetMapping("nghiphep")
    public String index(Model model) {
        // Lấy thông tin Authentication từ SecurityContextHolder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        // Kiểm tra kiểu dữ liệu của principal
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails currentUser = (CustomUserDetails) principal;
            Integer currentUserId = currentUser.getUser().getId();

            // Lấy danh sách nghỉ phép của nhân viên
            List<NghiPhep> listnp = nghiPhepService.getByEmployeeId(currentUserId);
            model.addAttribute("listnp", listnp);
        } else {
            model.addAttribute("error", "Không tìm thấy thông tin người dùng.");
            return "error";
        }

        return "employee/nghiphep/index"; // Trả về trang danh sách nghỉ phép
    }

    @GetMapping("add-nghiphep")
    public String add(Model model) {
    	NghiPhep nghiPhep = new NghiPhep();
        model.addAttribute("dsnp", nghiPhep);
        return "employee/nghiphep/addnp";
    }

    @PostMapping("add-nghiphep")
    public String save(@ModelAttribute("dsnp") NghiPhep nghiPhep, Model model) {
        // Lấy thông tin người dùng hiện tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        // Kiểm tra kiểu dữ liệu của principal
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails currentUser = (CustomUserDetails) principal;
            Users employee = currentUser.getUser(); // Lấy thông tin người dùng hiện tại

            // Gán thông tin nhân viên vào đối tượng NghiPhep
            nghiPhep.setEmployee(employee);

            // Kiểm tra xem status có được gán chưa, nếu chưa thì gán giá trị mặc định
            if (nghiPhep.getStatus() == null) {
                nghiPhep.setStatus(LeaveStatus.pending); // Gán mặc định là pending
            }

            // Lưu thông tin nghỉ phép
            if (nghiPhepService.create(nghiPhep)) {
                return "redirect:/employee/nghiphep";
            } else {
                model.addAttribute("error", "Lỗi khi lưu nghỉ phép.");
                return "employee/nghiphep/addnp";
            }
        } else {
            model.addAttribute("error", "Không tìm thấy thông tin người dùng.");
            return "error";
        }
    }

}
