package com.DoAnJavaWeb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.DoAnJavaWeb.models.Role;
import com.DoAnJavaWeb.models.UserRole;
import com.DoAnJavaWeb.models.Users;
import com.DoAnJavaWeb.repository.RoleRepository;
import com.DoAnJavaWeb.repository.UserRepository;
import com.DoAnJavaWeb.repository.UserRoleRepository;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private RoleRepository roleRepository;

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
	public void saveUserRole(UserRole userRole) {
		userRoleRepository.save(userRole);
	}

//    @Override
//    public Role findRoleByName(String roleName) {
//        return roleRepository.findByName(roleName);
//    }
	@Override
	public Role findRoleByName(String roleName) {
		Role role = roleRepository.findByName(roleName);
		if (role == null) {
			logger.error("Role not found: " + roleName);
		} else {
			logger.info("Role found: " + role.getName());
		}
		return role;
	}

	@Override
	public Users findById(Integer id) {
		return this.userRepository.findById(id).orElse(null);
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
	
	@Override
	public String getEmailById(Integer id) {
		Users user = userRepository.findById(id).orElse(null);
		return user != null ? user.getEmail(): null;
	}
}
