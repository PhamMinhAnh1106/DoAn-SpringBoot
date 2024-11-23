package com.DoAnJavaWeb;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));//này dùng để băm cái pass ra
	}
}
