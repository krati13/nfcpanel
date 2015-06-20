package com.springsecurity.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springsecurity.auth.model.Role;
import com.springsecurity.auth.model.User;
import com.springsecurity.auth.service.RoleServiceImpl;
import com.springsecurity.auth.service.UserServiceImpl;
import com.springsecurity.auth.util.MailSendUtil;

@Controller
public class UserInfoController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	private MailSendUtil mailSendUtil;
	private RoleServiceImpl roleServiceImpl;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addUser(@RequestBody User user) {
		if (user.getRole() != null){
			userServiceImpl.addUser(user);
			mailSendUtil.sendMail("umanager427@gmail.com", user.getEmail(), "Account Created!", "<div style='text-align:center;font-size:20px:color:red'>Your account has been created</div> ");
			//System.out.println(user+"adding");
		}

		Map<String, Object> mapObj = new HashMap<String, Object>();
		List<Role> listRoles = null;
		try {
			listRoles = roleServiceImpl.getRoleList();
		} catch (Exception e) {

		}
		mapObj.put("user", user);
		mapObj.put("roles", listRoles);
		return mapObj;
	}

	@RequestMapping(value = "/getRolesList.*", method = RequestMethod.GET)
	public @ResponseBody List<Role> getRolesList() {
		List<Role> listRoles = null;
		try {
			listRoles = roleServiceImpl.getRoleList();
		} catch (Exception e) {

		}
		return listRoles;
	}

	public RoleServiceImpl getRoleServiceImpl() {
		return roleServiceImpl;
	}

	public void setRoleServiceImpl(RoleServiceImpl roleServiceImpl) {
		this.roleServiceImpl = roleServiceImpl;
	}

	public MailSendUtil getMailSendUtil() {
		return mailSendUtil;
	}

	public void setMailSendUtil(MailSendUtil mailSendUtil) {
		this.mailSendUtil = mailSendUtil;
	}

}
