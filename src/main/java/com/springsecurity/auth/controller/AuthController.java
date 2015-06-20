package com.springsecurity.auth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.auth.model.User;

@Controller
public class AuthController {
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping("/home")
	public void homePage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String redirectURL = null;
		String context = request.getContextPath();
		User user = (User)session.getAttribute("user");
		if(user==null){
			redirectURL = context+"/login";
		}else if (user.getRole().getRoleName().equalsIgnoreCase("Admin")){
			redirectURL = context+"/admin/dashboard";
		}else{
			redirectURL = context+"/merchant/dashboard";
		}
		response.sendRedirect(redirectURL);
	}
}
