package com.DoAnJavaWeb.service;

import java.util.List;
import com.DoAnJavaWeb.models.Role;
import com.DoAnJavaWeb.models.UserRole;
import com.DoAnJavaWeb.models.Users;

public interface UserService {
    Users findByUsername(String username);
    List<Users> getAll();
    Boolean create(Users user);
    Users findById(Integer id);
    Boolean update(Users user);
    Boolean delete(Integer id);
    Role findRoleByName(String roleName);
    void saveUserRole(UserRole userRole);
	String getEmailById(Integer id);
}
