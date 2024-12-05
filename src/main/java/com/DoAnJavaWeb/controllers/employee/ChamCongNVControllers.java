package com.DoAnJavaWeb.controllers.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DoAnJavaWeb.models.CustomUserDetails;
import com.DoAnJavaWeb.models.ChamCong;
import com.DoAnJavaWeb.service.ChamCongService;

@Controller
@RequestMapping("/employee")
public class ChamCongNVControllers {

    @Autowired
    private ChamCongService chamCongService;

    @GetMapping("chamcong")
    public String index(Model model) {
        // Lấy thông tin Authentication từ SecurityContextHolder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal(); // Lấy principal từ authentication
        
        // Kiểm tra kiểu dữ liệu của principal
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails currentUser = (CustomUserDetails) principal; // Ép kiểu an toàn
            Integer currentUserId = currentUser.getUser().getId(); // Lấy ID người dùng hiện tại
            
            // Lấy danh sách chấm công theo ID nhân viên
            List<ChamCong> listcc = chamCongService.getByEmployeeId(currentUserId);
            model.addAttribute("listcc", listcc);
        } else {
            // Nếu principal không phải là CustomUserDetails, xử lý lỗi
            model.addAttribute("error", "Không tìm thấy thông tin người dùng.");
            return "error";  // Trả về trang lỗi hoặc xử lý phù hợp
        }

        return "employee/chamcong/index"; // Trả về view chấm công
    }
}