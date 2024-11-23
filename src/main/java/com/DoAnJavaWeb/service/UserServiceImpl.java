package com.DoAnJavaWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnJavaWeb.models.Users;
import com.DoAnJavaWeb.repository.UserRepository;

@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public Users findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
}
