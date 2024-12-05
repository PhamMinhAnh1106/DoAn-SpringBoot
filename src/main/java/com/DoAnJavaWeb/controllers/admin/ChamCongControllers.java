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
        List<ChamCong> listcc = chamCongService.getAll();
        model.addAttribute("listcc", listcc);
        return "admin/listchamcong/indexLCC";
    }

    @GetMapping("add-chamcong")
    public String add(Model model) {
        ChamCong chamcong = new ChamCong();
        chamcong.setEnabled(true);
        model.addAttribute("dscc", chamcong);
        return "admin/listchamcong/addLcc";
    }

    @PostMapping("add-chamcong")
    public String save(@ModelAttribute("dscc") ChamCong chamCong) {
        if (chamCongService.create(chamCong)) {
            return "redirect:/admin/listchamcong";
        } else {
            return "admin/listchamcong/addLcc";
        }
    }

    @GetMapping("edit-chamcong/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        ChamCong chamCong = chamCongService.findById(id);
        if (chamCong != null) {
            model.addAttribute("chamCong", chamCong);
            return "admin/listchamcong/editLcc";
        } else {
            return "redirect:/admin/listchamcong";
        }
    }

    @PostMapping("edit-chamcong/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("chamCong") ChamCong chamCong) {
        chamCong.setId(id); // Cập nhật ID cho chamCong
        if (chamCongService.update(chamCong)) {
            return "redirect:/admin/listchamcong";
        } else {
            return "admin/listchamcong/editLcc"; // Nếu có lỗi, quay lại trang chỉnh sửa
        }
    }
}