package com.DoAnJavaWeb.service;

import java.util.List;

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
		return userRepository.findByUsername(username);
	}
	@Override
	public List<Users> getAll() {
		return this.userRepository.findAll();
	}
	@Override
	public Boolean create(Users user) {
		try {
			this.userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Users findById(Integer id) {
		
		return this.userRepository.findById(id).get();
	}
	@Override
	public Boolean update(Users user) {
		try {
			this.userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Boolean delete(Integer id) {
		try {
			this.userRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
