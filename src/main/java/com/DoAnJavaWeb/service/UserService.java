package com.DoAnJavaWeb.service;

import com.DoAnJavaWeb.models.Users;

public interface UserService {
	Users findByUsername(String username);
}
