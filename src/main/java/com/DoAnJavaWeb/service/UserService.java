package com.DoAnJavaWeb.service;

import java.util.List;

import com.DoAnJavaWeb.models.Users;

public interface UserService {
	Users findByUsername(String username);
	
	List<Users> getAll();
	//crud
	Boolean create(Users user);
	Users findById(Integer id);
	Boolean update(Users user);
	Boolean delete(Integer id);
}
