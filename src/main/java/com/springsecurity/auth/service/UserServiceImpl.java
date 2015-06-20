package com.springsecurity.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.auth.dao.UserDao;
import com.springsecurity.auth.model.User;

@Service("userServiceImpl")
public class UserServiceImpl{

	@Autowired
	UserDao userDao;
	
	public User addUser(User user) {
		user=userDao.addUser(user);
		return user;
	}
	
	public void addUserRole(int user_id,int role_id) {
		userDao.addUserRole(user_id, role_id);
	}

	public int editUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
