package com.DoAnJavaWeb.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.DoAnJavaWeb.models.CustomUserDetails;
import com.DoAnJavaWeb.models.UserRole;
import com.DoAnJavaWeb.models.Users;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userService.findByUsername(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("Khong tim thay account hop le!");
		}
		Collection<GrantedAuthority> gratedAuthoritySet = new HashSet<GrantedAuthority>();
		Set<UserRole> roles = user.getUserRoles();
		
		
		for (UserRole userRole : roles) {
			gratedAuthoritySet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
		}
		
		 System.out.println("Loading user: " + username);
	        System.out.println("Found roles: " + gratedAuthoritySet);
		return new CustomUserDetails(user, gratedAuthoritySet);
	}

}
