package com.DoAnJavaWeb;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.DoAnJavaWeb.service.CustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); // hàm đọc pass được băm ra so sánh
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/*").permitAll()
//				.requestMatchers("/admin/**").permitAll()
				.requestMatchers("/admin/**")
				.hasAuthority("ADMIN")
				.requestMatchers("/employee/**")
				.hasAuthority("EMPLOYEE")
				.anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(authenticationSuccessHandler()))// gọi hàm để kiểm tra role
//				.logout(logout->logout.logoutUrl("/admin-logout").logoutSuccessUrl("/login"))
				.logout(logout->logout.logoutUrl("/employee-logout").logoutSuccessUrl("/login"));
		return http.build(); 
	}
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/static/**","/layout/**","assets/**");
	}
	private AuthenticationSuccessHandler authenticationSuccessHandler() {// cái này dùng để kiểm tra if else xem là role nào
	return new AuthenticationSuccessHandler() {
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			// TODO Auto-generated method stub
			 String role = authentication.getAuthorities().toString();

	            // Kiểm tra vai trò và điều hướng đến trang tương ứng
	            if (role.contains("ADMIN")) {
	                response.sendRedirect("/admin");  // Điều hướng đến trang admin nếu là ADMIN
	            } else if (role.contains("EMPLOYEE")) {
	                response.sendRedirect("/employee");  // Điều hướng đến trang employee nếu là EMPLOYEE
	            } else {
	                response.sendRedirect("/default");  // Điều hướng đến trang mặc định nếu không phải ADMIN hay EMPLOYEE
	            }
		}
	};
	}
}
