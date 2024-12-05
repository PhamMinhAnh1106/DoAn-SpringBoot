package com.DoAnJavaWeb.controllers.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DoAnJavaWeb.models.CustomUserDetails;
import com.DoAnJavaWeb.models.Users;
import com.DoAnJavaWeb.service.UserService;

@Controller
@RequestMapping("/employee")
public class TTCaNhanControllers {

    @Autowired
    private UserService userService;

    @GetMapping("ttcanhan")
    public String index(Model model) {
        // Lấy thông tin Authentication từ SecurityContextHolder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal(); // Lấy principal từ authentication
        
        // Kiểm tra kiểu dữ liệu của principal
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails currentUser = (CustomUserDetails) principal; // Ép kiểu an toàn
            Integer currentUserId = currentUser.getUser().getId(); // Lấy ID người dùng hiện tại
            
            // Lấy thông tin chi tiết của người dùng
            Users user = userService.findById(currentUserId);
            if (user != null) {
                model.addAttribute("user", user); // Gắn thông tin người dùng vào model
            } else {
                model.addAttribute("error", "Không tìm thấy thông tin người dùng.");
                return "error";  // Trả về trang lỗi nếu không tìm thấy thông tin
            }
        } else {
            // Nếu principal không phải là CustomUserDetails, xử lý lỗi
            model.addAttribute("error", "Không tìm thấy thông tin người dùng.");
            return "error";  // Trả về trang lỗi hoặc xử lý phù hợp
        }

        return "employee/ttcanhan/index"; // Trả về view thông tin cá nhân
    }
}