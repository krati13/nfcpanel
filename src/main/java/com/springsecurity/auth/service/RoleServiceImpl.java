/**
JMathur
 */

package com.springsecurity.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springsecurity.auth.dao.RoleDao;
import com.springsecurity.auth.model.Role;


public class RoleServiceImpl {
	
	@Autowired
	RoleDao roleDao;
	
	private List<Role> roleList;
	
	public void setRoles() {
		setRoleList(roleDao.getRoleList());
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
}
