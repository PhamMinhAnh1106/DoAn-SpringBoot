package com.DoAnJavaWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnJavaWeb.models.Luong;
import com.DoAnJavaWeb.repository.LuongRepository;

@Service
public class LuongServiceImpl implements LuongService {

    @Autowired
    private LuongRepository luongRepository;

    @Override
    public List<Luong> getAll() {
        // Trả về danh sách lương từ repository
        return luongRepository.findAll();
    }

    @Override
    public Boolean create(Luong luong) {
        // Tạo mới một bản ghi lương
        try {
            luongRepository.save(luong);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Luong findById(Integer id) {
        // Tìm lương theo ID
        return luongRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean update(Luong luong) {
        // Cập nhật thông tin lương
        try {
            luongRepository.save(luong);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        // Xóa lương theo ID
        try {
            luongRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}