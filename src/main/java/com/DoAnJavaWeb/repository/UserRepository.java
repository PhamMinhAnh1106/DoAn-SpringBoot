package com.DoAnJavaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnJavaWeb.models.Users;
import java.util.List;


public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findByUsername(String username);
}
