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

import com.DoAnJavaWeb.models.Luong;
import com.DoAnJavaWeb.models.Users;
import com.DoAnJavaWeb.service.LuongService;
import com.DoAnJavaWeb.service.UserService;  // Thêm import UserService

@Controller
@RequestMapping("/admin")
public class LuongControllers {

    @Autowired
    private LuongService luongService;

    @Autowired
    private UserService userService;  // Inject UserService

    // Hiển thị danh sách các bản ghi lương
    @GetMapping("listluong")
    public String index(Model model) {
        List<Luong> listLuong = luongService.getAll();
        model.addAttribute("listLuong", listLuong);
        return "admin/listluong/indexLuong";
    }

    // Hiển thị form thêm mới lương
    @GetMapping("add-luong")
    public String add(Model model) {
        List<Users> employees = userService.getAll();  // Lấy danh sách nhân viên
        model.addAttribute("employees", employees);   // Thêm danh sách nhân viên vào model
        model.addAttribute("luong", new Luong());    // Tạo đối tượng lương mới
        return "admin/listluong/addLuong";
    }

    // Xử lý form thêm mới lương
    @PostMapping("add-luong")
    public String save(@ModelAttribute("luong") Luong luong) {
        if (luongService.create(luong)) {
            return "redirect:/admin/listluong";  // Chuyển hướng về danh sách
        } else {
            return "admin/listluong/addLuong";  // Nếu tạo không thành công, trở lại trang thêm mới
        }
    }

    // Hiển thị form chỉnh sửa lương
    @GetMapping("edit-luong/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Luong luong = luongService.findById(id);
        if (luong != null) {
            List<Users> employees = userService.getAll();  // Lấy danh sách nhân viên
            model.addAttribute("employees", employees);   // Thêm danh sách nhân viên vào model
            model.addAttribute("luong", luong);
            return "admin/listluong/editLuong";  // Hiển thị form chỉnh sửa
        } else {
            return "redirect:/admin/listluong";  // Nếu không tìm thấy, chuyển về trang danh sách
        }
    }

    // Xử lý form chỉnh sửa lương
    @PostMapping("edit-luong/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("luong") Luong luong) {
        // Tính toán lại totalSalary khi chỉnh sửa
        if (luong.getBasicSalary() != null) {
            luong.setTotalSalary(luong.calculateTotalSalary());  // Gọi phương thức tính totalSalary
        }

        luong.setIdSalary(id);  // Đảm bảo ID không bị thay đổi

        if (luongService.update(luong)) {
            return "redirect:/admin/listluong";
        } else {
            return "admin/listluong/editLuong"; // Nếu có lỗi, quay lại trang chỉnh sửa
        }
    }

}
